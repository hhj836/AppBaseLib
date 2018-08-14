package com.hhj.appbase.base;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by hhj on 2018/6/15.
 */

public interface ViewInterface {
   <T> LifecycleTransformer<T> getLifecycleTransFormer();
    Activity getActivityImp();
    void showLoading();
    void hideLoading();
    void showEmptyView();
    void hideEmptyView();
    void showNoNetView();
    void hideNoNetView();
    void showToast(String msg);
    //需要显示空布局，无网络布局，获取数据用此方法
    void refreshData();
}
