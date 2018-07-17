package com.hhj.appbase.list;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by hhj on 2018/3/23.
 */

public class ListConfig<M>  {
    public boolean mRefreshAble=true;
    public boolean mLoadMoreAble=true;
    public boolean mAutoLoadMore=true;
    //listActivity resId  必须包含SmartRefreshLayout和RecyclerView且id与baseListActivity查找id一致
    public int mContainerResId;
    //下拉刷新主色调
    public int  srlPrimaryColor;
    //字体颜色
    public int  srlAccentColor;
    //数据为0的resId
    public int mContainerEmptyResId;
    //动画类型
    public int  animationType;
    public View noNetView;
    public BaseAnimation baseAnimation;
    public Observer<List<M>>  refreshObserver;
    public Observer<List<M>>  lodMoreObserver;
    private ListConfig() {

    }
    public static class Builder<M>{
        private ListConfig<M> target;
        public Builder(){
            target=new ListConfig<M>();
        }
        public Builder<M> setRefreshAble(boolean  enable){
            target.mRefreshAble=enable;
            return  Builder.this;

        }
        public Builder<M> setLoadMoreAble(boolean  enable){
            target.mLoadMoreAble=enable;
            return  Builder.this;
        }
        public Builder<M> setAutoLoadMore(boolean  enable){
            target.mAutoLoadMore=enable;
            return  Builder.this;

        }
        public  Builder<M> setSrlPrimaryColor(int color){
            target.srlPrimaryColor=color;
            return  Builder.this;
        }
        public  Builder<M> setSrlAccentColor(int color){
            target.srlAccentColor=color;
            return  Builder.this;
        }
        public Builder<M> setEmptyResId(int resId){
            target.mContainerEmptyResId=resId;
            return  Builder.this;
        }
        public Builder<M> setContentResId(int resId){
            target.mContainerResId=resId;
            return  Builder.this;
        }
        public Builder<M> setAnimType(int animationType){
            target.animationType=animationType;
            return  Builder.this;

        }
        public Builder<M> setNoNetView(View v){
            target.noNetView=v;
            return  Builder.this;
        }
        public Builder<M> setAnimation(BaseAnimation baseAnimation){
            target.baseAnimation=baseAnimation;
            return  Builder.this;

        }
        public  Builder<M> setRefreshObserver(Observer<List<M>> refreshObserver){
            target.refreshObserver=refreshObserver;
            return  Builder.this;

        }
        public  Builder<M> setLoadMoreObserver(Observer<List<M>> loadMoreObserver){
            target.lodMoreObserver=loadMoreObserver;
            return  Builder.this;

        }
        public  ListConfig<M> build(){
            return  target;
        }

    }
}
