package com.hhj.appbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hhj.appbase.R;
import com.hhj.appbase.view.titlebar.widget.CommonTitleBar;


/**
 * Created by hhj on 2018/3/22.
 */

public abstract  class BaseViewFragment<P extends Presenter> extends BeamFragment<P> {

    private View root_base;


    public View getRoot() {
        return root_base;
    }
    FrameLayout fm_content_base;
    public CommonTitleBar commonTitleBar;
    AppBarLayout appBarLayout_base;
    public  int getTitleResId(){
        return 0;
    };
    public   void onPreInitView(){};

    public abstract  int getLayoutId();
    public abstract void initView();
    /**
     * 是否滑动titlebar
     * @return
     */
    public boolean isSlideTitleBar(){
        return  false;
    }
    public void  disableTitleSlide(){
        if(appBarLayout_base!=null&&appBarLayout_base.getChildCount()>0&&appBarLayout_base.getChildAt(0).getLayoutParams() instanceof AppBarLayout.LayoutParams){
            AppBarLayout.LayoutParams  layoutParams= (AppBarLayout.LayoutParams) appBarLayout_base.getChildAt(0).getLayoutParams();
            layoutParams.setScrollFlags(0);
        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=null;
        if(getTitleResId()!=0){
            view=inflater.inflate(R.layout.ac_base_view,null);
            appBarLayout_base=view.findViewById(R.id.appbar_base);
            RelativeLayout rl_title=view.findViewById(R.id.rl_title);
            commonTitleBar= (CommonTitleBar) View.inflate(mActivity,getTitleResId(),null);
            rl_title.addView(commonTitleBar);
            if(!isSlideTitleBar()){
                disableTitleSlide();
            }
            fm_content_base=view.findViewById(R.id.fm_content_base);
            if(getLayoutId()!=0){
                View content=getLayoutInflater().inflate(getLayoutId(),null);
                fm_content_base.addView(content);
            }




        }else {
            view=inflater.inflate(getLayoutId(),null);
        }
        root_base=view;
        onPreInitView();
        initView();
        return  view;
    }


}
