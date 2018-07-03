package com.hhj.mywork.model;

import android.content.Context;
import android.util.Log;

import com.hhj.appbase.model.AbsModel;

/**
 * Created by hhj on 2018/7/3.
 */

public class TestModel extends AbsModel{
    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        Log.d("TestModel","TestModel-onAppCreate-"+Thread.currentThread().getName());
    }

    @Override
    protected void onAppCreateOnBackThread(Context ctx) {
        Log.d("TestModel","TestModel-onAppCreate-"+Thread.currentThread().getName());
    }
}
