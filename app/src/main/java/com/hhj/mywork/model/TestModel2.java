package com.hhj.mywork.model;

import android.content.Context;
import android.util.Log;

import com.hhj.appbase.model.AbsModel;

/**
 * Created by hhj on 2018/7/3.
 */

public class TestModel2 extends AbsModel {
    @Override
    protected void onAppCreateOnBackThread(Context ctx) {
        Log.d("TestModel","TestModel2-onAppCreate-"+Thread.currentThread().getName());
    }
}
