package com.hhj.mywork.app;


import android.app.Application;
import android.text.TextUtils;

import com.hhj.appbase.base.ActivityLifeCycleDelegate;
import com.hhj.appbase.base.ActivityLifeCycleDelegateProvider;
import com.hhj.appbase.base.Beam;
import com.hhj.appbase.base.BeamAppCompatActivity;
import com.hhj.appbase.baseconfig.BaseConfig;
import com.hhj.mywork.ActivityDelegate;
import com.hhj.mywork.R;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.utils.SkinPreference;

import static skin.support.SkinCompatManager.SKIN_LOADER_STRATEGY_NONE;


/**
 * Created by hhj on 2018/3/22.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTheme();
        Beam.init(getApplicationContext());
        Beam.setActivityLifeCycleDelegateProvider(new ActivityLifeCycleDelegateProvider() {
            @Override
            public ActivityLifeCycleDelegate createActivityLifeCycleDelegate(BeamAppCompatActivity activity) {
                return new ActivityDelegate(activity);
            }
        });
    }
    public void initTheme(){
        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                //.setSkinAllActivityEnable(false)                        // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
                .loadSkin();
        String skin = SkinPreference.getInstance().getSkinName();
        int strategy = SkinPreference.getInstance().getSkinStrategy();
        if (TextUtils.isEmpty(skin) || strategy == SKIN_LOADER_STRATEGY_NONE) {
            BaseConfig.DEFAULT.colorPrimary= getApplicationContext().getResources().getColor(R.color.global_color);
            BaseConfig.DEFAULT.colorContent=getApplicationContext().getResources().getColor(R.color.global_bg);
        }
        BaseConfig.DEFAULT.titleBarLeftResId= R.mipmap.navi_back_white;
    }
}
