package com.hhj.appbase.base;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;


public class FragmentLifeCycleDelegate {
    private BeamFragment fm;

    public FragmentLifeCycleDelegate(BeamFragment fm) {
        this.fm = fm;
    }

    public BeamFragment getFragment() {
        return fm;
    }

    public void onCreate(Bundle savedInstanceState){}
    public void onActivityCreated(@Nullable Bundle savedInstanceState){}
    public void onDestroyView(){}
    public void onDestroy(){}
    public void onSaveInstanceState(@NonNull Bundle outState){}
    public void onStart(){}
    public void onResume(){}
    public void onPause(){}
    public void onActivityResult(int requestCode, int resultCode, Intent data){}


}
