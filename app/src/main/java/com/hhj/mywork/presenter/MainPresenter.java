package com.hhj.mywork.presenter;

import android.support.annotation.NonNull;

import com.hhj.appbase.base.Presenter;
import com.hhj.mywork.MainActivity;

/**
 * Created by hhj on 2018/3/23.
 */

public class MainPresenter extends Presenter<MainActivity> {
    @Override
    protected void onCreateView(@NonNull MainActivity view) {
        super.onCreateView(view);
        view.setText("点击打开图片列表");

    }
}
