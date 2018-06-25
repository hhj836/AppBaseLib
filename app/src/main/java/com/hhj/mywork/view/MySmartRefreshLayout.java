package com.hhj.mywork.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.hhj.mywork.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import skin.support.utils.SkinPreference;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by hhj on 2018/6/25.
 */

public class MySmartRefreshLayout extends SmartRefreshLayout implements SkinCompatSupportable {
    private int accentColor;
    private int primaryColor;

    public MySmartRefreshLayout(Context context) {
        this(context,null);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySmartRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SmartRefreshLayout);
        accentColor = ta.getColor(R.styleable.SmartRefreshLayout_srlAccentColor, INVALID_ID);
        primaryColor = ta.getColor(R.styleable.SmartRefreshLayout_srlPrimaryColor, INVALID_ID);
        ta.recycle();
        applySkin();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        applySkin();
    }

    @Override
    public void applySkin() {
        if(accentColor!=INVALID_ID&&primaryColor!=INVALID_ID){
            String skin = SkinPreference.getInstance().getSkinName();
            setPrimaryColors(getResources().getColor(TextUtils.isEmpty(skin)?R.color.colorPrimary: R.color.colorPrimary_night),getResources().getColor(TextUtils.isEmpty(skin)?R.color.global_bg:R.color.global_bg_night));
        }


    }
}
