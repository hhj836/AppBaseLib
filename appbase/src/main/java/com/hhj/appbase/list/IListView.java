package com.hhj.appbase.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.hhj.appbase.base.ViewInterface;

/**
 * Created by hhj on 2018/4/10.
 */

public interface IListView extends ViewInterface{
     void finishRefresh();
     void  finishLoadMore(boolean noMore,boolean success);
     void initList();
     RecyclerView.LayoutManager createLayoutManger();
     RecyclerView.ItemDecoration createItemDecoration();
}
