package com.hhj.appbase.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hhj.appbase.R;
import com.hhj.appbase.utils.ToastUtils;
import com.hhj.appbase.view.titlebar.widget.CommonTitleBar;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;


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
    //点击事件自行实现
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
    public void refreshData() {
        hideEmptyView();
        hideNoNetView();

    }

    @Override
    public Activity getActivityImp() {
        return BaseViewActivity.this;
    }
    @Override
    public void showToast(String msg) {
        ToastUtils.showLongToast(BaseViewActivity.this,msg);
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
        return this.<T>bindUntilEvent(ActivityEvent.DESTROY);
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
            LinearLayout ll_title=findViewById(R.id.ll_title);
            commonTitleBar= (CommonTitleBar) View.inflate(BaseViewActivity.this,getTitleResId(),null);
            ll_title.addView(commonTitleBar);
            if(!isSlideTitleBar()){
                disableTitleSlide();
            }
            fm_content_base=findViewById(R.id.fm_content_base);
            if(getLayoutId()!=0){
                View content=getLayoutInflater().inflate(getLayoutId(),null);
                fm_content_base.addView(content);
            }
        }else {
            setContentView(getLayoutId());

        }
        emptyView=getEmptyView();
        setNoNetView(getNoNetView());
        if(commonTitleBar!=null){
            commonTitleBar.setListener(getTitleBarListener());
        }
        onPreInitView();


    }
    protected void setNoNetView(View v){
        noNetView=v;
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (startActivitySelfCheck(intent)) {
            // 查看源码得知 startActivity 最终也会调用 startActivityForResult
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    private String mActivityJumpTag;
    private long mActivityJumpTime;

    /**
     * 检查当前 Activity 是否重复跳转了，不需要检查则重写此方法并返回 true 即可
     *
     * @param intent          用于跳转的 Intent 对象
     * @return                检查通过返回true, 检查不通过返回false
     */
    protected boolean startActivitySelfCheck(Intent intent) {
        // 默认检查通过
        boolean result = true;
        // 标记对象
        String tag;
        if (intent.getComponent() != null) { // 显式跳转
            tag = intent.getComponent().getClassName();
        }else if (intent.getAction() != null) { // 隐式跳转
            tag = intent.getAction();
        }else {
            return result;
        }

        if (tag.equals(mActivityJumpTag) && mActivityJumpTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false;
        }

        // 记录启动标记和时间
        mActivityJumpTag = tag;
        mActivityJumpTime = SystemClock.uptimeMillis();
        return result;
    }
}
