package com.hhj.mywork.app;


import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hhj.appbase.base.ActivityLifeCycleDelegate;
import com.hhj.appbase.base.ActivityLifeCycleDelegateProvider;
import com.hhj.appbase.base.Beam;
import com.hhj.appbase.base.BeamAppCompatActivity;
import com.hhj.mywork.ActivityDelegate;
import com.hhj.mywork.R;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.utils.SkinPreference;



/**
 * Created by hhj on 2018/3/22.
 */

public class App extends Application {
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                StoreHouseHeader houseHeader=new StoreHouseHeader(context);
                houseHeader.initWithString("WWWWWW");
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter footer= new ClassicsFooter(context);
                footer.setFinishDuration(0);
                ClassicsFooter.REFRESH_FOOTER_LOADING="Σ(っ °Д °;)っ";
                ClassicsFooter.REFRESH_FOOTER_NOTHING="╮(๑•́ ₃•̀๑)╭木有了~";

                return footer;
            }
        });
    }
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
        Log.d("App","App=="+skin);
        Log.d("App","App=="+strategy);
    }
}
