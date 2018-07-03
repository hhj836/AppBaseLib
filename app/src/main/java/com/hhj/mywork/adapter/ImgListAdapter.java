package com.hhj.mywork.adapter;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhj.mywork.R;
import com.hhj.mywork.activity.ImgListActivity;
import com.hhj.mywork.utlis.Picture;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
        int width = (int) (dm.widthPixels/2- ImgListActivity.convertDpToPixel(5.0f,imgPicture.getContext())*2);//宽度为屏幕宽度一半
        int height = data.getHeight()*width/data.getWidth();//计算View的高度
        params.width=width;
        params.height = height;
        imgPicture.setLayoutParams(params);
        //imgPicture.setImageResource(R.mipmap.ic_launcher);
        Glide.with(imgPicture.getContext())
                .load(data.getSrc())
                .bitmapTransform(new RoundedCornersTransformation(imgPicture.getContext(), (int) ImgListActivity.convertDpToPixel(5,imgPicture.getContext()), 0, RoundedCornersTransformation.CornerType.ALL))
                .crossFade()
                .into(imgPicture);
    }
}
