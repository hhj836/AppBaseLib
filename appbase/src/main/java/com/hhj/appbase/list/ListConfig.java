package com.hhj.appbase.list;


import com.chad.library.adapter.base.animation.BaseAnimation;

/**
 * Created by hhj on 2018/3/23.
 */

public class ListConfig  {
    public boolean mRefreshAble=true;
    public boolean mLoadMoreAble=true;
    public boolean mAutoLoadMore=false;
    //listActivity resId  必须包含SmartRefreshLayout和RecyclerView且id一致
    public int mContainerResId;
    //下拉刷新主色调
    public int  srlPrimaryColor;
    //字体颜色
    public int  srlAccentColor;
    //数据为0的resId
    public int mContainerEmptyResId;
    //动画类型
    public int  animationType;
    public BaseAnimation baseAnimation;


    private ListConfig() {

    }
    public static class Builder{
        private ListConfig target;
        public Builder(){
            target=new ListConfig();
        }
        public Builder setRefreshAble(boolean  enable){
            target.mRefreshAble=enable;
            return  Builder.this;

        }
        public Builder setLoadMoreAble(boolean  enable){
            target.mLoadMoreAble=enable;
            return  Builder.this;
        }
        public Builder setAutoLoadMore(boolean  enable){
            target.mAutoLoadMore=enable;
            return  Builder.this;

        }
        public  Builder setSrlPrimaryColor(int color){
            target.srlPrimaryColor=color;
            return  Builder.this;
        }
        public  Builder setSrlAccentColor(int color){
            target.srlAccentColor=color;
            return  Builder.this;
        }
        public Builder setEmptyResId(int resId){
            target.mContainerEmptyResId=resId;
            return  Builder.this;
        }
        public Builder setAnimType(int animationType){
            target.animationType=animationType;
            return  Builder.this;

        }
        public Builder setAnimation(BaseAnimation baseAnimation){
            target.baseAnimation=baseAnimation;
            return  Builder.this;

        }
        public  ListConfig build(){
            return  target;
        }

    }
}
