package com.hhj.mywork.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.hhj.mywork.R;
import com.hhj.mywork.fragment.ImgListFragment;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

/**
 * Created by hhj on 2018/4/10.
 */

public class TestFragmentActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.ac_test;
    }


    @Override
    public void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new ImgListFragment()).commitAllowingStateLoss();
    }
}
