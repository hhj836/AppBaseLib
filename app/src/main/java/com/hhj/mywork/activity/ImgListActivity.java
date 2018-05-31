package com.hhj.mywork.activity;


import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;

import com.hhj.appbase.base.RequiresPresenter;
import com.hhj.appbase.exception.ApiException;
import com.hhj.appbase.list.BaseListActivity;
import com.hhj.appbase.list.ListConfig;
import com.hhj.mywork.presenter.ImgListPresenter;
import com.hhj.mywork.utlis.Picture;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;


/**
 * Created by hhj on 2018/3/24.
 */
@RequiresPresenter(ImgListPresenter.class)
public class ImgListActivity extends BaseListActivity<ImgListPresenter> {
    @Override
    public RecyclerView.LayoutManager createLayoutManger() {
        return new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        SpaceDecoration itemDecoration = new SpaceDecoration((int) convertDpToPixel(8,this));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        return itemDecoration;
    }


    @Override
    public void initView() {
        setTitleText("图片列表");

    }
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
