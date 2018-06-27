package com.hhj.mywork.presenter;

import android.support.annotation.NonNull;

import com.hhj.appbase.base.BasePresenter;
import com.hhj.appbase.base.Presenter;
import com.hhj.mywork.MainActivity;
import com.hhj.mywork.activity.BaseActivity;
import com.hhj.mywork.viewinterface.IMainActivity;

/**
 * Created by hhj on 2018/3/23.
 */

public class MainPresenter extends BasePresenter<IMainActivity> {
    @Override
    protected void onCreateView(@NonNull IMainActivity view) {
        super.onCreateView(view);
        view.setText("点击打开图片列表");

    }

    @Override
    public void initData() {
        super.initData();
    }
}
