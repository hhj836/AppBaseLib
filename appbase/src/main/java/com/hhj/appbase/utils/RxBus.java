package com.hhj.appbase.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import static com.trello.rxlifecycle2.internal.Preconditions.checkNotNull;

/**
 * Created by hhj on 2017/12/18.
 */

public class RxBus {
    public   static RxBus instance=new RxBus();

    private  final Subject<Object> subject=PublishSubject.create().toSerialized();
    private RxBus(){

    }
    public static  RxBus getInstance(){
        if(instance==null){
            instance=new RxBus();
            return instance;
        }
        return  instance;
    }

    public <T> void post(T  t){
        subject.onNext(t);
        Log.d("RxBus","post-----");

    }
    public static class EventObserverBuilder<T>{
        private LifecycleTransformer<Object> lifecycleTransformer;
        private Consumer<T> consumer;

        public EventObserverBuilder<T> create(@NonNull LifecycleTransformer<Object> lifecycleTransformer){
            this.lifecycleTransformer=lifecycleTransformer;
            return  EventObserverBuilder.this;

        }
        public  EventObserverBuilder<T> setConsumer(@NonNull Consumer<T> consumer){
            this.consumer=consumer;
            return  EventObserverBuilder.this;
        }
        public void  build(Class<T> clazz){
            checkNotNull(consumer, "consumer == null");
            checkNotNull(lifecycleTransformer, "lifecycleTransformer == null");
            RxBus.getInstance().toObservable().compose(lifecycleTransformer).ofType(clazz).subscribe(consumer, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.d("RxBus","error-----"+throwable.getMessage());
                }
            });

        }

    }


    public Observable<Object> toObservable(){
        return  subject;
    }


}
