package com.hhj.appbase.view.titlebar.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;

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

    private int rightTextColor=INVALID_ID;

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
        } catch (Exception e) {
        }




    }
}
