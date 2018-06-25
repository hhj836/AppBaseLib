package com.hhj.appbase.base;

import android.app.Activity;

/**
 * Created by hhj on 2018/6/15.
 */

public interface ViewInterface {
    Activity getActivity();
    void showLoading();
    void hideLoading();
    void showEmpty();
    void showNoNet();
    void showMessage(String message);
}
