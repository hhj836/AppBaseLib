package com.hhj.appbase.base;

/**
 * Created by zhuchenxi on 15/10/6.
 */
public interface ActivityLifeCycleDelegateProvider {
    ActivityLifeCycleDelegate createActivityLifeCycleDelegate(BeamAppCompatActivity activity);
}
