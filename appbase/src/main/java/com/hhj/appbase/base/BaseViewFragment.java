package com.hhj.appbase.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.hhj.appbase.R;
import com.hhj.appbase.baseconfig.BaseConfig;


/**
 * Created by hhj on 2018/3/22.
 */

public abstract  class BaseViewFragment<P extends Presenter> extends BeamFragment<P> {
    RippleView btn_left_base;
    RippleView btn_right_base;
    TextView tv_left_base;
    TextView   tv_right_base;
    TextView tv_title_base;
    AppBarLayout appBarLayout_base;
    RelativeLayout rl_title_base;

    RelativeLayout rl_title_content_base;
    FrameLayout fm_content_base;

    private View root_base;

    BaseConfig  config;
    public View getRoot() {
        return root_base;
    }
    public   void onPreInitView(){};
    public int getTitleContentResId(){
        return  0;
    }
    public  boolean  isShowTitle(){
        return  false;
    }
    public boolean isContainsCommonViews(){
        return  false;
    }
    public boolean isSlideTitleBar(){
        return  false;
    }
    public abstract  int getLayoutId();
    public abstract void initView();
    public void  disableTitleSlide(){
        if(appBarLayout_base!=null&&appBarLayout_base.getChildCount()>0&&appBarLayout_base.getChildAt(0).getLayoutParams() instanceof AppBarLayout.LayoutParams){
            AppBarLayout.LayoutParams  layoutParams= (AppBarLayout.LayoutParams) appBarLayout_base.getChildAt(0).getLayoutParams();
            layoutParams.setScrollFlags(0);
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        config=BaseConfig.DEFAULT;
        View  view=null;
        if(isContainsCommonViews()){
            view=inflater.inflate(R.layout.ac_base_view,null);
            btn_left_base=view.findViewById(R.id.btn_left_base);
            btn_left_base.setVisibility(View.INVISIBLE);
            btn_left_base.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    Toast.makeText(getContext(),"左边按钮点击",Toast.LENGTH_SHORT);
                }

            });
            btn_right_base=view.findViewById(R.id.btn_right_base);
            btn_right_base.setVisibility(View.INVISIBLE);
            tv_left_base=view.findViewById(R.id.tv_left_base);
            tv_right_base=view.findViewById(R.id.tv_right_base);
            appBarLayout_base=view.findViewById(R.id.appbar_base);
            rl_title_base=view.findViewById(R.id.rl_title_base);
            rl_title_content_base=view.findViewById(R.id.rl_title_content_base);
            fm_content_base=view.findViewById(R.id.fm_content_base);
            if(BaseConfig.DEFAULT.colorContent!=0){
                fm_content_base.setBackgroundColor(BaseConfig.DEFAULT.colorContent);
            }
            if(config.colorPrimary!=0){
                rl_title_base.setBackgroundColor(BaseConfig.DEFAULT.colorPrimary);
            }
            if(getTitleContentResId()!=0){

                View titleContent=mActivity.getLayoutInflater().inflate(getTitleContentResId(),null);
                rl_title_content_base.removeAllViews();
                rl_title_content_base.addView(titleContent);
            }else {
                tv_title_base=view.findViewById(R.id.tv_title_base);
            }
            if(getLayoutId()!=0){
                View content=getActivity().getLayoutInflater().inflate(getLayoutId(),null);
                fm_content_base.removeAllViews();
                fm_content_base.addView(content);
            }
            if(isShowTitle()){
                setTitleVisible();
            }else {
                setTitleGone();
            }
        }else {
            view=inflater.inflate(getLayoutId(),null);
        }
        root_base=view;
        if(!isSlideTitleBar()){
            disableTitleSlide();
        }
        onPreInitView();
        initView();
        return  view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public BaseViewFragment setBtnRightText(String s){
        btn_right_base.setVisibility(View.VISIBLE);
        tv_right_base.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tv_right_base.setText(s);
        return  BaseViewFragment.this;


    }
    public BaseViewFragment setBtnRightImgRes(int res){
        btn_right_base.setVisibility(View.VISIBLE);
        tv_right_base.setBackgroundResource(res);
        tv_right_base.setText("");
        return  BaseViewFragment.this;
    }
    public BaseViewFragment setBtnLeftText(String s){
        btn_left_base.setVisibility(View.VISIBLE);
        tv_left_base.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tv_left_base.setText(s);
        return  BaseViewFragment.this;

    }
    public BaseViewFragment setBtnLeftImgRes(int res){
        btn_left_base.setVisibility(View.VISIBLE);
        tv_left_base.setBackgroundResource(res);
        tv_left_base.setText("");
        return  BaseViewFragment.this;

    }
    public BaseViewFragment setOnBtnLeftClickListener(RippleView.OnRippleCompleteListener onClickListener){
        btn_left_base.setVisibility(View.VISIBLE);
        btn_left_base.setOnRippleCompleteListener(onClickListener);
        return  BaseViewFragment.this;

    }
    public BaseViewFragment setOnBtnRightClickListener(RippleView.OnRippleCompleteListener onClickListener){
        btn_right_base.setVisibility(View.VISIBLE);
        btn_right_base.setOnRippleCompleteListener(onClickListener);
        return  BaseViewFragment.this;

    }
    public void setTitleGone(){
        rl_title_base.setVisibility(View.GONE);
        appBarLayout_base.setVisibility(View.GONE);
    }
    public void setTitleVisible(){
        rl_title_base.setVisibility(View.VISIBLE);
        appBarLayout_base.setVisibility(View.VISIBLE);
    }
}
