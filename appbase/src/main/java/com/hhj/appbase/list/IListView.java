package com.hhj.appbase.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hhj on 2018/4/10.
 */

public interface IListView {
     void finishRefresh();
     void  finishLoadMore(boolean noMore);
     void showEmptyView();
     void initList();
     RecyclerView.LayoutManager createLayoutManger();
     RecyclerView.ItemDecoration createItemDecoration();
     Activity getMActivity();
}
