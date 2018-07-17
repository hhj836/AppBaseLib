package com.hhj.appbase.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hhj.appbase.R;
import com.hhj.appbase.utils.ToastUtils;
import com.hhj.appbase.view.titlebar.widget.CommonTitleBar;
import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 * Created by hhj on 2018/3/22.
 */

public abstract  class BaseViewFragment<P extends Presenter> extends BeamFragment<P> implements ViewInterface{

    private View root_base;


    public View getRoot() {
        return root_base;
    }
    public View emptyView;
    public View noNetView;
    public View getEmptyView(){
        return null;
    }
    public View getNoNetView(){
        return null;
    }
    FrameLayout fm_content_base;
    public CommonTitleBar commonTitleBar;
    AppBarLayout appBarLayout_base;
    public  int getTitleResId(){
        return 0;
    };
    public   void onPreInitView(){};

    public abstract  int getLayoutId();
    public abstract void initView();
    public CommonTitleBar.OnTitleBarListener getTitleBarListener(){
        return new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                switch (action){
                    case CommonTitleBar.ACTION_LEFT_TEXT:
                    case CommonTitleBar.ACTION_LEFT_BUTTON:
                        mActivity.finish();
                        break;
                }
            }
        };
    }
    @Override
    public Activity getActivityImp() {
        return mActivity;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showLongToast(mActivity,msg);
    }

    @Override
    public void showLoading() {
        if(commonTitleBar!=null){
            commonTitleBar.showCenterProgress();
        }
    }

    @Override
    public void hideEmptyView() {
        if(emptyView!=null&&emptyView.getParent()!=null&&fm_content_base!=null){
            fm_content_base.removeView(emptyView);
        }
    }

    @Override
    public void hideNoNetView() {
        if(noNetView!=null&&noNetView.getParent()!=null&&fm_content_base!=null){
            fm_content_base.removeView(noNetView);
        }
    }

    @Override
    public void hideLoading() {
        if(commonTitleBar!=null){
            commonTitleBar.dismissCenterProgress();
        }

    }

    @Override
    public void showEmptyView() {
        if(fm_content_base!=null&&emptyView!=null){
            fm_content_base.addView(emptyView);
        }

    }

    @Override
    public void showNoNetView() {
        if(fm_content_base!=null&&noNetView!=null){
            fm_content_base.addView(noNetView);
        }
    }
    @Override
    public <T> LifecycleTransformer<T> getLifecycleTransFormer() {
        return this.<T>bindToLifecycle();
    }
    @Override
    public void refreshData() {
        hideEmptyView();
        hideNoNetView();

    }

    /**
     * 是否滑动titlebar
     * @return
     */
    public boolean isSlideTitleBar(){
        return  false;
    }
    public void  disableTitleSlide(){
        if(appBarLayout_base!=null&&appBarLayout_base.getChildCount()>0&&appBarLayout_base.getChildAt(0).getLayoutParams() instanceof AppBarLayout.LayoutParams){
            AppBarLayout.LayoutParams  layoutParams= (AppBarLayout.LayoutParams) appBarLayout_base.getChildAt(0).getLayoutParams();
            layoutParams.setScrollFlags(0);
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view;
        if(getTitleResId()!=0){
            view=inflater.inflate(R.layout.ac_base_view,null);
            appBarLayout_base=view.findViewById(R.id.appbar_base);
            RelativeLayout rl_title=view.findViewById(R.id.rl_title);
            commonTitleBar= (CommonTitleBar) View.inflate(mActivity,getTitleResId(),null);
            rl_title.addView(commonTitleBar);
            if(!isSlideTitleBar()){
                disableTitleSlide();
            }
            fm_content_base=view.findViewById(R.id.fm_content_base);
            if(getLayoutId()!=0){
                View content=getLayoutInflater().inflate(getLayoutId(),null);
                fm_content_base.addView(content);
            }

        }else {
            view=inflater.inflate(getLayoutId(),null);
        }
        emptyView=getEmptyView();
        noNetView=getNoNetView();
        if(emptyView!=null){
            emptyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshData();
                }
            });
        }
        if(noNetView!=null){
            noNetView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshData();
                }
            });
        }
        root_base=view;
        onPreInitView();
        initView();
        return  view;
    }


}
