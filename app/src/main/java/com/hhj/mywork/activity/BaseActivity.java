package com.hhj.mywork.activity;

import com.hhj.appbase.base.BaseViewActivity;
import com.hhj.appbase.base.Presenter;

import butterknife.ButterKnife;

/**
 * Created by hhj on 2018/3/23.
 */

public abstract  class BaseActivity<P extends Presenter> extends BaseViewActivity<P> {
    @Override
    public void onPreInitView() {
        ButterKnife.bind(this);
    }

}
