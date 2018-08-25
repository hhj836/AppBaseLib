package com.hhj.appbase.base;

import android.content.Context;

import com.hhj.appbase.model.ModelManager;


/**
 * Created by Mr.Jude on 2015/7/26.
 */
public final class Beam {
    private static ActivityLifeCycleDelegateProvider mActivityLIfeCycleDelegateProvider;
    private static FragmentLifeCycleDelegateProvider mFragmentLIfeCycleDelegateProvider;


    public static ActivityLifeCycleDelegate createActivityLifeCycleDelegate(BeamAppCompatActivity activity) {
        if (mActivityLIfeCycleDelegateProvider!=null)
            return mActivityLIfeCycleDelegateProvider.createActivityLifeCycleDelegate(activity);
        else return null;
    }

    public static FragmentLifeCycleDelegate createFragmentLifeCycleDelegate(BeamFragment fragment) {
        if (mFragmentLIfeCycleDelegateProvider!=null)
            return mFragmentLIfeCycleDelegateProvider.createFragmentLifeCycleDelegate(fragment);
        else return null;
    }

    public static void setActivityLifeCycleDelegateProvider(ActivityLifeCycleDelegateProvider activityLifeCycleDelegateClass){
        mActivityLIfeCycleDelegateProvider = activityLifeCycleDelegateClass;
    }
    public static void setFragmentLifeCycleDelegateProvider(FragmentLifeCycleDelegateProvider fragmentLifeCycleDelegateClass){
        mFragmentLIfeCycleDelegateProvider = fragmentLifeCycleDelegateClass;
    }
    public static void init(Context ctx){
        ModelManager.init(ctx);
    }
    public static  void destroy(){
        ModelManager.destroy();
    }

}
