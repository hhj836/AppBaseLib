package com.hhj.mywork.adapter;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhj.mywork.R;
import com.hhj.mywork.utlis.Picture;

import java.util.List;

/**
 * Created by hhj on 2018/3/24.
 */

public class ImgListAdapter extends BaseQuickAdapter<Picture,BaseViewHolder> {
    public ImgListAdapter(List<Picture> data) {
        super(R.layout.item_img_list, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Picture data) {
       final ImageView imgPicture=helper.getView(R.id.img);
        ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

        DisplayMetrics dm = imgPicture.getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels/2;//宽度为屏幕宽度一半
        int height = data.getHeight()*width/data.getWidth();//计算View的高度
        params.width=width;
        params.height = height;
        imgPicture.setLayoutParams(params);
        //imgPicture.setImageResource(R.mipmap.ic_launcher);
        Glide.with(imgPicture.getContext())
                .load(data.getSrc())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imgPicture.setImageBitmap(resource);
                    }
                });
    }
}
