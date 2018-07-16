package com.hhj.appbase.list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhj.appbase.R;
import com.hhj.appbase.base.BaseViewActivity;
import com.hhj.appbase.view.WrapContentLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.internal.InternalClassics;

import static com.trello.rxlifecycle2.internal.Preconditions.checkNotNull;

/**
 * Created by hhj on 2018/3/23.
 */

public abstract class BaseListActivity<P extends BaseListPresenter> extends BaseViewActivity<P> implements IListView {
    public SmartRefreshLayout refreshLayout;
    public RecyclerView recyclerView;
    private ListConfig mListConfig;


    @Override
    public RecyclerView.LayoutManager createLayoutManger(){
        return  null;
    }
    @Override
    public RecyclerView.ItemDecoration createItemDecoration(){
        return  null;
    }
    @Override
    public int getLayoutId() {
        return getPresenter().getListConfig().mContainerResId==0?R.layout.base_list:getPresenter().getListConfig().mContainerResId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListConfig=getPresenter().getListConfig();
        refreshLayout=findViewById(R.id.refreshLayout);
        recyclerView=findViewById(R.id.recyclerView);
        checkNotNull(refreshLayout,"refreshLayout is null---> id 'refreshLayout'");
        checkNotNull(recyclerView,"recyclerView is null---> id 'recyclerView'");
        initList();

    }
    @SuppressLint("RestrictedApi")
    @Override
    public void initList() {
        refreshLayout.setEnableRefresh(mListConfig.mRefreshAble);
        refreshLayout.setEnableLoadMore(mListConfig.mLoadMoreAble);
        refreshLayout.setEnableAutoLoadMore(mListConfig.mAutoLoadMore);
        refreshLayout.setOnRefreshListener(getPresenter());
        refreshLayout.setOnLoadMoreListener(getPresenter());
        if(refreshLayout.getRefreshFooter() instanceof InternalClassics){
            InternalClassics internalClassics= (InternalClassics) refreshLayout.getRefreshFooter();
            internalClassics.setFinishDuration(0);
        }
        if(mListConfig.srlPrimaryColor!=0||mListConfig.srlAccentColor!=0){
            refreshLayout.getRefreshHeader().setPrimaryColors(mListConfig.srlPrimaryColor==0?getResources().getColor(R.color.base_default_refresh_bg):
                    mListConfig.srlPrimaryColor,mListConfig.srlAccentColor==0?Color.WHITE:mListConfig.srlAccentColor);
        }
        if(createLayoutManger()!=null){
            recyclerView.setLayoutManager(createLayoutManger());
        }else {
            recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(BaseListActivity.this));
        }
        if(createItemDecoration()!=null){
            recyclerView.addItemDecoration(createItemDecoration());
        }
        if(mListConfig.animationType!=0){
            getPresenter().getAdapter().openLoadAnimation(mListConfig.animationType);
        }
        if(mListConfig.baseAnimation!=null){
            getPresenter().getAdapter().openLoadAnimation(mListConfig.baseAnimation);
        }
        if(getPresenter().getAdapter().getEmptyView()!=null&&getPresenter().getAdapter().getEmptyView().getParent()!=null){
            ViewGroup group= (ViewGroup) getPresenter().getAdapter().getEmptyView().getParent();
            group.removeView(getPresenter().getAdapter().getEmptyView());
        }

        recyclerView.setAdapter(getPresenter().getAdapter());
        refreshLayout.autoRefresh();
    }

    @Override
    public void finishRefresh(){
        refreshLayout.finishRefresh();

    }

    @Override
    public void  finishLoadMore(boolean noMore){
        if(noMore){
            refreshLayout.finishLoadMoreWithNoMoreData();
        }else {
            refreshLayout.finishLoadMore();
        }

    }

    @Override
    public void showEmptyView(){
        if( getPresenter().getAdapter().getEmptyViewCount()==0){
            View  emptyView=View.inflate(BaseListActivity.this,mListConfig.mContainerEmptyResId==0?R.layout.empty_view:mListConfig.mContainerEmptyResId,null);
            getPresenter().getAdapter().setEmptyView(emptyView);
        }
    }


}
