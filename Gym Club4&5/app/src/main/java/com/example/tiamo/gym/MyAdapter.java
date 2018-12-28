package com.example.tiamo.gym;

import android.graphics.ColorSpace;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.example.tiamo.gym.videomodel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<videomodel, BaseViewHolder> {

    public MyAdapter(@LayoutRes int layoutResId, @Nullable List<videomodel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, videomodel item) {
        //可链式调用赋值
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent())
                .setImageResource(R.id.iv_img, item.getImgUrl());

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}