package com.hhj.mywork.presenter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.hhj.appbase.list.BaseListPresenter;
import com.hhj.appbase.list.IListView;
import com.hhj.appbase.list.ListConfig;
import com.hhj.appbase.utils.ToastUtils;
import com.hhj.mywork.R;
import com.hhj.mywork.activity.ImgListActivity;
import com.hhj.mywork.adapter.ImgListAdapter;
import com.hhj.mywork.utlis.DataProvider;
import com.hhj.mywork.utlis.Picture;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by hhj on 2018/3/24.
 */

public class ImgListPresenter extends BaseListPresenter<IListView,Picture> {
    @Override
    public BaseQuickAdapter<Picture, BaseViewHolder> createAdapter() {
        ImgListAdapter adapter=new ImgListAdapter(new ArrayList<Picture>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Picture picture= (Picture) adapter.getData().get(position);
                ToastUtils.showShortToast(getActivity(),picture.getSrc());

            }
        });
        return adapter;
    }

    @Override
    public ListConfig<Picture> createListConfig() {
        return new ListConfig.Builder<Picture>().setNoNetView(View.inflate(getView().getActivityImp(),R.layout.test_empty,null)).setAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{ObjectAnimator.ofFloat(view, "scaleY", 0, 1.0f).setDuration(500),
                        ObjectAnimator.ofFloat(view, "scaleX", 0, 1.0f).setDuration(500),
                        ObjectAnimator.ofFloat(view, "alpha", 0, 1.0f).setDuration(500)

                 };
            }
        }).setContentResId(R.layout.common_list).build();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        super.onLoadMore(refreshLayout);
        Observable.just(DataProvider.getPictures(page)).subscribe(getLoadMoreObserver());
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        super.onRefresh(refreshLayout);
        Observable.just(DataProvider.getPictures(page)).subscribe(getRefreshObserver());
    }
}
