package com.hhj.mywork.activity;


import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.hhj.appbase.base.RequiresPresenter;
import com.hhj.appbase.exception.ApiException;
import com.hhj.appbase.list.BaseListActivity;
import com.hhj.appbase.list.ListConfig;
import com.hhj.appbase.view.titlebar.widget.CommonTitleBar;
import com.hhj.mywork.R;
import com.hhj.mywork.app.App;
import com.hhj.mywork.presenter.ImgListPresenter;
import com.hhj.mywork.view.SpaceDecoration;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import skin.support.widget.SkinCompatSupportable;


/**
 * Created by hhj on 2018/3/24.
 */
@RequiresPresenter(ImgListPresenter.class)
public class ImgListActivity extends BaseListActivity<ImgListPresenter> implements SkinCompatSupportable {
    @Override
    public boolean isSlideTitleBar() {
        return true;
    }

    @Override
    public int getTitleResId() {
        return R.layout.title_common;
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManger() {
        return new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        SpaceDecoration itemDecoration = new SpaceDecoration((int) convertDpToPixel(4,this));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        return itemDecoration;
    }

    @Override
    public void initView() {
       // setTitleText("图片列表");
        commonTitleBar.setDoubleClickListener(new CommonTitleBar.OnTitleBarDoubleClickListener() {
            @Override
            public void onClicked(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        Observable.timer(2,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                showNoNetView();
            }
        });

    }

    @Override
    public void refreshData() {
        super.refreshData();
        refreshLayout.autoRefresh();
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ImgListActivity","onDestroy");
    }

    @Override
    public void applySkin() {
        Log.d("ImgListActivity","applySkin");
    }
}
