package com.hhj.appbase.base;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleTransformer;

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
    void showToast(String message);
}
