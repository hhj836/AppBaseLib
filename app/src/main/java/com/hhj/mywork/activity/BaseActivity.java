package com.hhj.mywork.activity;


import android.view.View;

import com.hhj.appbase.base.BaseViewActivity;
import com.hhj.appbase.base.Presenter;
import com.hhj.appbase.view.titlebar.widget.CommonTitleBar;
import com.hhj.mywork.R;

import butterknife.ButterKnife;

/**
 * Created by hhj on 2018/3/23.
 */

public abstract  class BaseActivity<P extends Presenter> extends BaseViewActivity<P> {
    @Override
    public int getTitleResId() {
        return R.layout.title_common;
    }

    @Override
    public void onPreInitView() {
        ButterKnife.bind(this);
        if(getTitleResId()!=0){
            commonTitleBar.setListener(getTitleBarListener());
        }
    }

}
