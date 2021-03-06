package com.hhj.appbase.list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhj.appbase.R;
import com.hhj.appbase.base.BaseViewFragment;
import com.hhj.appbase.view.WrapContentLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.internal.InternalClassics;

import static com.trello.rxlifecycle2.internal.Preconditions.checkNotNull;

/**
 * Created by hhj on 2018/4/4.
 */

public abstract class BaseListFragment<P extends BaseListPresenter> extends BaseViewFragment<P> implements IListView{
    public SmartRefreshLayout refreshLayout;
    public RecyclerView recyclerView;
    private ListConfig mListConfig;
    private View emptyView;
    public View getEmptyView(){
        return emptyView;
    }


    @Override
    public int getLayoutId() {
        return getPresenter().getListConfig().mContainerResId==0? R.layout.base_list:getPresenter().getListConfig().mContainerResId;
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManger(){
        return  null;
    }
    @Override
    public RecyclerView.ItemDecoration createItemDecoration(){
        return  null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=super.onCreateView(inflater, container, savedInstanceState);
        mListConfig=getPresenter().getListConfig();
        refreshLayout=view.findViewById(R.id.refreshLayout);
        recyclerView=view.findViewById(R.id.recyclerView);
        checkNotNull(refreshLayout,"refreshLayout is null---> id 'refreshLayout'");
        checkNotNull(recyclerView,"recyclerView is null---> id 'recyclerView'");
        initList();

        return view;

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
            if(refreshLayout.getRefreshHeader()!=null){
                refreshLayout.getRefreshHeader().setPrimaryColors(mListConfig.srlPrimaryColor==0?getResources().getColor(R.color.base_default_refresh_bg):
                        mListConfig.srlPrimaryColor,mListConfig.srlAccentColor==0? Color.WHITE:mListConfig.srlAccentColor);
            }
            if(refreshLayout.getRefreshFooter()!=null){
                refreshLayout.getRefreshFooter().setPrimaryColors(mListConfig.srlPrimaryColor==0?getResources().getColor(R.color.base_default_refresh_bg):
                        mListConfig.srlPrimaryColor,mListConfig.srlAccentColor==0? Color.WHITE:mListConfig.srlAccentColor);
            }

        }
        if(createLayoutManger()!=null){
            recyclerView.setLayoutManager(createLayoutManger());
        }else {
            recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(mActivity));
        }
        if(createItemDecoration()!=null){
            recyclerView.addItemDecoration(createItemDecoration());
        }
        if(mListConfig.noNetView!=null){
            setNoNetView(mListConfig.noNetView);
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
        if(mListConfig.isShowEmpty){
            emptyView=View.inflate(mActivity,mListConfig.mContainerEmptyResId==0?R.layout.empty_view:mListConfig.mContainerEmptyResId,null);
        }
        recyclerView.setAdapter(getPresenter().getAdapter());
        getPresenter().onRefresh(refreshLayout);


    }

    @Override
    public void finishRefresh(){
        refreshLayout.finishRefresh();

    }
    @Override
    public void refreshData() {
        super.refreshData();
        refreshLayout.autoRefresh();
    }

    @Override
    public void  finishLoadMore(boolean noMore,boolean success){
        if(!success){
            refreshLayout.finishLoadMore(false);
            refreshLayout.setEnableLoadMore(false);
            return;
        }
        if(noMore){
            refreshLayout.finishLoadMoreWithNoMoreData();
        }else {
            refreshLayout.finishLoadMore();
        }

    }
    @Override
    public void showEmptyView(){
        if(emptyView!=null){
            if( getPresenter().getAdapter().getEmptyViewCount()==0){
                getPresenter().getAdapter().setEmptyView(emptyView);

            }
        }

    }
}
