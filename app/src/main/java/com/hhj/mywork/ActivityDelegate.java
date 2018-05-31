package com.hhj.mywork;

import android.os.Build;
import android.os.Bundle;

import com.hhj.appbase.base.ActivityLifeCycleDelegate;
import com.hhj.appbase.base.BeamAppCompatActivity;

/**
 * Created by hhj on 2018/4/11.
 */

public class ActivityDelegate extends ActivityLifeCycleDelegate {
    public ActivityDelegate(BeamAppCompatActivity act) {
        super(act);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
