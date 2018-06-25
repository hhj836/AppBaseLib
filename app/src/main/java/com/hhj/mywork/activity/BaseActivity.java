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
    public CommonTitleBar.OnTitleBarListener getTitleBarListener(){
        return new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action){
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                    case CommonTitleBar.ACTION_LEFT_BUTTON:
                        finish();
                        break;
                }
            }
        };
    }
    @Override
    public void onPreInitView() {
        ButterKnife.bind(this);
        if(getTitleResId()!=0){
            commonTitleBar.setListener(getTitleBarListener());
        }
    }

}
