package com.hhj.appbase.view.titlebar.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.hhj.appbase.R;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;

/**
 * Created by hhj on 2018/6/23.
 */

public class SkinCompatCommonTitleHelper extends SkinCompatHelper {
    private final CommonTitleBar mView;

    private int centerTextColor=INVALID_ID;
    private int centerSubTextColor=INVALID_ID;
    private int titleBarColor=INVALID_ID;
    private int leftTextColor=INVALID_ID;
    private int leftDrawable=INVALID_ID;
    private int leftImageResource=INVALID_ID;
    private int rightImageResource=INVALID_ID;
    private int rightTextColor=INVALID_ID;

    private int rippleRes=INVALID_ID;

   // private int centerSearchBgResource=INVALID_ID;

    public SkinCompatCommonTitleHelper(CommonTitleBar commonTitleBar) {
        mView = commonTitleBar;
    }
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = null;
        try {
            a = mView.getContext().obtainStyledAttributes(attrs,R.styleable.CommonTitleBar, defStyleAttr, 0);
            titleBarColor = a.getResourceId(R.styleable.CommonTitleBar_titleBarColor, INVALID_ID);
            centerTextColor = a.getResourceId(R.styleable.CommonTitleBar_centerTextColor, INVALID_ID);
            centerSubTextColor=a.getResourceId(R.styleable.CommonTitleBar_centerSubTextColor, INVALID_ID);
            leftTextColor=a.getResourceId(R.styleable.CommonTitleBar_leftTextColor, INVALID_ID);
            rightTextColor=a.getResourceId(R.styleable.CommonTitleBar_rightTextColor, INVALID_ID);
            leftDrawable=a.getResourceId(R.styleable.CommonTitleBar_leftDrawable, INVALID_ID);
            leftImageResource=a.getResourceId(R.styleable.CommonTitleBar_leftImageResource, INVALID_ID);
            rightImageResource=a.getResourceId(R.styleable.CommonTitleBar_rightImageResource, INVALID_ID);
            rippleRes=a.getResourceId(R.styleable.CommonTitleBar_rippleBgRes, INVALID_ID);
            //centerSearchBgResource=a.getResourceId(R.styleable.CommonTitleBar_centerSearchBg, INVALID_ID);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
        applySkin();
    }



    @Override
    public void applySkin() {
        try {
            centerTextColor=checkResourceId(centerTextColor);
            if(centerTextColor!=INVALID_ID){
                mView.getCenterTextView().setTextColor(SkinCompatResources.getColorStateList(mView.getContext(), centerTextColor));
            }
            centerSubTextColor=checkResourceId(centerSubTextColor);
            if(centerSubTextColor!=INVALID_ID){
                mView.getCenterSubTextView().setTextColor(SkinCompatResources.getColorStateList(mView.getContext(), centerSubTextColor));
            }
            titleBarColor=checkResourceId(titleBarColor);
            if(titleBarColor!=INVALID_ID){
                mView.setBackgroundColor(SkinCompatResources.getColor(mView.getContext(),titleBarColor));
            }
            leftTextColor=checkResourceId(leftTextColor);
            if(leftTextColor!=INVALID_ID){
                mView.getLeftTextView().setTextColor(SkinCompatResources.getColorStateList(mView.getContext(), leftTextColor));
            }
            rightTextColor=checkResourceId(rightTextColor);
            if(rightTextColor!=INVALID_ID){
                mView.getRightTextView().setTextColor(SkinCompatResources.getColorStateList(mView.getContext(), rightTextColor));
            }
            leftDrawable=checkResourceId(leftDrawable);
            if(leftDrawable!=INVALID_ID){
                TextView tvLeft=  mView.getLeftTextView();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    tvLeft.setCompoundDrawablesRelativeWithIntrinsicBounds(SkinCompatResources.getDrawable(mView.getContext(),leftDrawable), null, null, null);
                } else {
                    tvLeft.setCompoundDrawablesWithIntrinsicBounds(SkinCompatResources.getDrawable(mView.getContext(),leftDrawable), null, null, null);
                }

                }
            leftImageResource=checkResourceId(leftImageResource);
            if(leftImageResource!=INVALID_ID){
                mView.getLeftImageButton().setImageDrawable(SkinCompatResources.getDrawable(mView.getContext(),leftImageResource));
            }
            rightImageResource=checkResourceId(rightImageResource);
            if(rightImageResource!=INVALID_ID){
                mView.getRightImageButton().setImageDrawable(SkinCompatResources.getDrawable(mView.getContext(),rightImageResource));
            }
            rippleRes=checkResourceId(rippleRes);
            if(rippleRes!=INVALID_ID){
                if(mView.getLeftTextView()!=null){
                    mView.getLeftTextView().setBackground(SkinCompatResources.getDrawable(mView.getContext(),rippleRes));
                }
                if(mView.getLeftImageButton()!=null){
                    mView.getLeftImageButton().setBackground(SkinCompatResources.getDrawable(mView.getContext(),rippleRes));
                }
                if(mView.getRightTextView()!=null){
                    mView.getRightTextView().setBackground(SkinCompatResources.getDrawable(mView.getContext(),rippleRes));
                }
                if(mView.getRightImageButton()!=null){
                    mView.getRightImageButton().setBackground(SkinCompatResources.getDrawable(mView.getContext(),rippleRes));
                }
            }

        } catch (Exception e) {
        }




    }
}
