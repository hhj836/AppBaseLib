package com.hhj.mywork.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import com.hhj.appbase.base.RequiresPresenter;
import com.hhj.appbase.list.BaseListFragment;
import com.hhj.mywork.presenter.ImgListPresenter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

/**
 * Created by hhj on 2018/4/10.
 */
@RequiresPresenter(ImgListPresenter.class)
public class ImgListFragment extends BaseListFragment<ImgListPresenter> {

    @Override
    public RecyclerView.LayoutManager createLayoutManger() {
        return new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        SpaceDecoration itemDecoration = new SpaceDecoration((int) convertDpToPixel(8,mActivity));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        return itemDecoration;
    }
    @Override
    public void initView() {

    }
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
