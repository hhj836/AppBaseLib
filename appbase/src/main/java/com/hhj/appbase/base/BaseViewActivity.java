package com.hhj.appbase.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hhj.appbase.R;
import com.hhj.appbase.utils.ToastUtils;
import com.hhj.appbase.view.titlebar.widget.CommonTitleBar;
import com.jude.swipbackhelper.SwipeBackHelper;


/**
 * Created by hhj on 2018/3/22.
 */

public abstract class BaseViewActivity<P extends Presenter> extends BeamAppCompatActivity<P> implements ViewInterface{
    FrameLayout fm_content_base;
    public CommonTitleBar commonTitleBar;
    AppBarLayout appBarLayout_base;
    public  int getTitleResId(){
        return 0;
    };
    public   void onPreInitView(){};
    public abstract  int getLayoutId();
    public abstract  void initView();
    public View emptyView;
    public View noNetView;
    public View getEmptyView(){
        return null;
    }
    public View getNoNetView(){
        return null;
    }
    protected boolean isSwipeBackEnable() {
        return true;
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
    public Activity getActivityImp() {
        return BaseViewActivity.this;
    }
    @Override
    public void showMessage(String message) {
        ToastUtils.showLongToast(BaseViewActivity.this,message);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        if (isSwipeBackEnable() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SwipeBackHelper.getCurrentPage(this)
                    .setSwipeBackEnable(true)
                    .setSwipeSensitivity(0.5f)
                    .setSwipeEdgePercent(0.2f)
                    .setSwipeRelateEnable(true)
                    .setSwipeSensitivity(1);
        } else {
            SwipeBackHelper.getCurrentPage(this)
                    .setSwipeBackEnable(false);
        }
        if(getTitleResId()!=0){
            setContentView(R.layout.ac_base_view);
             appBarLayout_base=findViewById(R.id.appbar_base);
            RelativeLayout rl_title=findViewById(R.id.rl_title);
            commonTitleBar= (CommonTitleBar) View.inflate(BaseViewActivity.this,getTitleResId(),null);
            rl_title.addView(commonTitleBar);
            if(!isSlideTitleBar()){
                disableTitleSlide();
            }
            fm_content_base=findViewById(R.id.fm_content_base);
            if(getLayoutId()!=0){
                View content=getLayoutInflater().inflate(getLayoutId(),null);
                fm_content_base.addView(content);
            }
        }else {
            View content=getLayoutInflater().inflate(getLayoutId(),null);
            setContentView(content);

        }
        emptyView=getEmptyView();
        noNetView=getNoNetView();
        onPreInitView();
        initView();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
}
