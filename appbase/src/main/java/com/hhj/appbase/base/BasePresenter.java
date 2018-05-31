package com.hhj.appbase.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Parcelable;

import com.hhj.appbase.list.IListView;

import static com.trello.rxlifecycle2.internal.Preconditions.checkNotNull;

/**
 * Created by hhj on 2018/3/23.
 */

public class BasePresenter<ViewType> extends Presenter<ViewType> {
    public static final String KEY_ID = "intent_id";
    public static final String KEY_DATA = "intent_data";
    public Activity getActivity(){
        Activity activity=null;
        if(getView() instanceof Activity){
            activity= (Activity) getView();
        }else if(getView() instanceof Fragment){
            activity=((Fragment) getView()).getActivity();
        }else if(getView() instanceof IListView){
            activity=((IListView) getView()).getMActivity();
        }
        checkNotNull(activity,"activity is null--->"+getView().getClass().getSimpleName());
        return  activity;
    }
    public String getIdFromIntent(){
        return getActivity().getIntent().getStringExtra(KEY_ID);
    }

    public <M> M getDataFromIntent(){
        return getActivity().getIntent().getParcelableExtra(KEY_DATA);
    }

    public void startActivity(Intent intent){
        getActivity().startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> clazz){
        Activity activity = getActivity();
        activity.startActivity(new Intent(activity,clazz));
    }

    public void startActivityWithData(String id,Class<? extends Activity> clazz){
        Activity activity = getActivity();
        Intent i = new Intent(activity,clazz);
        i.putExtra(KEY_ID,id);
        activity.startActivity(i);
    }
    public void startActivityWithData(String id, Parcelable data, Class<? extends Activity> clazz){
        Activity activity = getActivity();
        Intent i = new Intent(activity,clazz);
        i.putExtra(KEY_ID,id);
        i.putExtra(KEY_DATA,data);
        activity.startActivity(i);
    }
    public void startActivityWithData(Parcelable data,Class<? extends Activity> clazz){
        Activity activity = getActivity();
        Intent i = new Intent(activity,clazz);
        i.putExtra(KEY_DATA,data);
        activity.startActivity(i);
    }
}
