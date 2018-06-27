package com.hhj.appbase.base;

import android.app.Activity;

/**
 * Created by hhj on 2018/6/15.
 */

public interface ViewInterface {
    Activity getActivityImp();
    void showLoading();
    void hideLoading();
    void showEmptyView();
    void hideEmptyView();
    void showNoNetView();
    void hideNoNetView();
    void showMessage(String message);
}
