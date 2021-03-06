package com.hhj.appbase.observer;

import android.content.Context;

import com.hhj.appbase.exception.ApiException;
import com.hhj.appbase.utils.LibLogUtil;
import com.hhj.appbase.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by hhj on 2018/3/24.
 */

public abstract  class DataObserver<M> implements Observer<M> {
    private Context context;
    protected Disposable disposable;
    public DataObserver(Context context){
        this.context=context;
    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable=d;

    }

    @Override
    public void onError(@NonNull Throwable e) {
        if(e instanceof ApiException&&context!=null){
            ApiException error= (ApiException) e;
            ToastUtils.showShortToast(context,error.getMsg());
        }else{
            LibLogUtil.error(getClass(),"error----"+e.getMessage());
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
