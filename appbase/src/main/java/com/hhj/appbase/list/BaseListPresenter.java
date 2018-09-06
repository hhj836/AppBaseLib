package com.hhj.appbase.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhj.appbase.base.BasePresenter;
import com.hhj.appbase.observer.DataObserver;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import io.reactivex.Observer;

import static com.trello.rxlifecycle2.internal.Preconditions.checkNotNull;

/**
 * Created by hhj on 2018/3/24.
 */

public abstract  class BaseListPresenter<T extends IListView,M> extends BasePresenter<T>implements OnRefreshListener,
        OnLoadMoreListener,IListPresenter<M>{
    public BaseQuickAdapter<M, BaseViewHolder> getAdapter() {
        return mAdapter;
    }
    public int page;
    public BaseQuickAdapter<M,BaseViewHolder>  mAdapter;
    public abstract BaseQuickAdapter<M,BaseViewHolder> createAdapter();

    private Observer<List<M>> refreshObserver;
    private Observer<List<M>> lodMoreObserver;
    public Observer<List<M>> getRefreshObserver(){
        return  refreshObserver;
    }
    public Observer<List<M>> getLoadMoreObserver(){
        return  lodMoreObserver;
    }
    public void onPageChange(int dataSize){
        page++;

    }

    public   ListConfig getListConfig() {
        return mListConfig;
    }

    private ListConfig<M>  mListConfig;

    @Override
    protected void onCreate(@NonNull T view, Bundle savedState) {
        super.onCreate(view, savedState);
        mListConfig=createListConfig();
        checkNotNull(mListConfig,"ListConfig is null");
        mAdapter=createAdapter();
        refreshObserver=mListConfig.refreshObserver==null?new DataObserver<List<M>>(getActivity()) {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().finishRefresh();
            }

            @Override
            public void onNext(@NonNull List<M> ms) {
                getView().finishRefresh();
                if(ms.size()==0){
                    mAdapter.setNewData(ms);
                    getView().showEmptyView();

                }else {
                    mAdapter.setNewData(ms);
                    onPageChange(ms.size());
                }

            }
        }:mListConfig.refreshObserver;
        lodMoreObserver=mListConfig.lodMoreObserver==null?new DataObserver<List<M>>(getActivity()) {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().finishLoadMore(false,false);
            }

            @Override
            public void onNext(@NonNull List<M> ms) {
                getView().finishLoadMore(ms.size()==0,true);
                if(ms.size()!=0){
                    mAdapter.addData(ms);
                    onPageChange(ms.size());
                }

            }
        }:mListConfig.lodMoreObserver;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=0;
        refreshLayout.finishLoadMore();
        refreshLayout.setNoMoreData(false);
        if(mListConfig.mLoadMoreAble){
            refreshLayout.setEnableLoadMore(true);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }


}
