package com.hhj.appbase.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.hhj.appbase.R;
import com.hhj.appbase.baseconfig.BaseConfig;




/**
 * Created by hhj on 2018/3/22.
 */

public abstract class BaseViewActivity<P extends Presenter> extends BeamAppCompatActivity<P> {
    RippleView btn_left_base;
    RippleView btn_right_base;//右按钮默认隐藏
    TextView tv_left_base;
    TextView   tv_right_base;
    TextView  tv_title_base;

    RelativeLayout rl_title_base;
    RelativeLayout rl_title_content_base;
    FrameLayout fm_content_base;
    AppBarLayout appBarLayout_base;
    BaseConfig  config;
    public   void onPreInitView(){};
    public abstract  int getLayoutId();
    public abstract  void initView();
    public int getTitleContentResId(){
        return  0;
    }
    /**
     * 是否显示titlebar
     * @return
     */
    public  boolean  isShowTitle(){
        return  true;
    }
    /**
     * 是否包含titlebar
     * @return
     */
    public boolean isContainsCommonViews(){
        return  true;
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
        config=BaseConfig.DEFAULT;
        super.onCreate(savedInstanceState);
        if(isContainsCommonViews()){
            setContentView(R.layout.ac_base_view);
            appBarLayout_base=findViewById(R.id.appbar_base);
            btn_left_base=findViewById(R.id.btn_left_base);
            btn_right_base=findViewById(R.id.btn_right_base);
            tv_left_base=findViewById(R.id.tv_left_base);
            tv_right_base=findViewById(R.id.tv_right_base);
            tv_title_base=findViewById(R.id.tv_title_base);
            rl_title_content_base=findViewById(R.id.rl_title_content_base);
            rl_title_base=findViewById(R.id.rl_title_base);
            if(config.colorPrimary!=0){
                rl_title_base.setBackgroundColor(config.colorPrimary);
            }
            if(config.titleBarLeftResId!=0){
                setBtnLeftImgRes(BaseConfig.DEFAULT.titleBarLeftResId);
            }
            if(getTitleContentResId()!=0){
                View titleContent=getLayoutInflater().inflate(getTitleContentResId(),null);
                rl_title_content_base.removeAllViews();
                rl_title_content_base.addView(titleContent);
            }
            fm_content_base=findViewById(R.id.fm_content_base);
            if(BaseConfig.DEFAULT.colorContent!=0){
                fm_content_base.setBackgroundColor(BaseConfig.DEFAULT.colorContent);
            }
            if(getLayoutId()!=0){
                View content=getLayoutInflater().inflate(getLayoutId(),null);
                fm_content_base.removeAllViews();
                fm_content_base.addView(content);
            }
            if(isShowTitle()){
                setTitleVisible();
            }else {
                setTitleGone();
            }
            btn_right_base.setVisibility(View.INVISIBLE);
            btn_left_base.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    finish();
                }
            });
            if(!isSlideTitleBar()){
                disableTitleSlide();
            }


        }else {
            setContentView(getLayoutId());
        }
        onPreInitView();
        initView();

    }
    public void setTitleText(String text){
        tv_title_base.setText(text);

    }
    public void setTitleBarColor(int resId){
        rl_title_base.setBackgroundColor(resId);

    }
    public void setTitleGone(){
        rl_title_base.setVisibility(View.GONE);
        appBarLayout_base.setVisibility(View.GONE);
    }
    public void setTitleVisible(){
        rl_title_base.setVisibility(View.VISIBLE);
        appBarLayout_base.setVisibility(View.VISIBLE);
    }
    public BaseViewActivity setBtnRightText(String s){
        btn_right_base.setVisibility(View.VISIBLE);
        tv_right_base.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tv_right_base.setText(s);
        return  BaseViewActivity.this;


    }
    public BaseViewActivity setBtnRightImgRes(int res){
        btn_right_base.setVisibility(View.VISIBLE);
        tv_right_base.setBackgroundResource(res);
        tv_right_base.setText("");
        return  BaseViewActivity.this;
    }
    public BaseViewActivity setBtnLeftText(String s){
        tv_left_base.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tv_left_base.setText(s);
        return  BaseViewActivity.this;

    }
    public BaseViewActivity setBtnLeftImgRes(int res){
        tv_left_base.setBackgroundResource(res);
        tv_left_base.setText("");
        return  BaseViewActivity.this;

    }
    public BaseViewActivity setOnBtnRightClickListener(RippleView.OnRippleCompleteListener onRippleCompleteListener){
        btn_right_base.setVisibility(View.VISIBLE);
        btn_right_base.setOnRippleCompleteListener(onRippleCompleteListener);
        return  BaseViewActivity.this;
    }
    public BaseViewActivity setOnBtnLeftClickListener(RippleView.OnRippleCompleteListener onRippleCompleteListener){
        btn_left_base.setOnRippleCompleteListener(onRippleCompleteListener);
        return  BaseViewActivity.this;
    }
}
