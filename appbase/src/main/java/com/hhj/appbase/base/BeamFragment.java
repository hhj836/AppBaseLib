package com.hhj.appbase.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * This view is an example of how a view should control it's presenter.
 * You can inherit from this class or copy/paste this class's code to
 * create your own view implementation.
 *
 */
public class BeamFragment<PresenterType extends Presenter> extends RxFragment {
    public BeamAppCompatActivity mActivity;
    private FragmentLifeCycleDelegate fragmentLifeCycleDelegate;
    @Override
    public void onAttach(Context context) {
        mActivity= (BeamAppCompatActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onCreate(savedInstanceState);
        }
        helper.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onActivityCreated(savedInstanceState);
        }
        helper.onPostCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onDestroyView();
        }
        helper.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onDestroy();
        }
        helper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onSaveInstanceState(outState);
        }
        helper.onSave(outState);
    }
    @Override
    public void onStart() {
        super.onStart();
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onResume();
        }
        helper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onPause();
        }
        helper.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(fragmentLifeCycleDelegate!=null){
            fragmentLifeCycleDelegate.onActivityResult(requestCode, resultCode, data);
        }
        helper.onResult(requestCode, resultCode, data);
    }

    protected final <E extends View> E $(@NonNull View view,@IdRes int id){
        return (E)view.findViewById(id);
    }

    protected final <E extends View> E viewId(@NonNull View view,@IdRes int id){
        return (E)view.findViewById(id);
    }

    public PresenterType getPresenter() {
        return helper.getPresenter();
    }

    private ViewHelper<PresenterType> helper = new ViewHelper<>(this);
}
