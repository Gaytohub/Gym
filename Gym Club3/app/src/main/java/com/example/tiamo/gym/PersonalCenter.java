package com.example.tiamo.gym;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.maiml.library.BaseItemLayout;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class PersonalCenter extends Fragment {
    @Nullable


    private BaseItemLayout layout;
    private BaseItemLayout layouttwo;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragement_personalcenter, container, false);
<<<<<<< HEAD
=======
        Bundle bundle = getArguments();
        String result = bundle.getString("TYPE");
>>>>>>> 第三次提交
        /*final Context contextThemeWrapper = new ContextThemeWrapper(
                getActivity(), R.style.TranslucentTheme);
        LayoutInflater localInflater = inflater
                .cloneInContext(contextThemeWrapper);

        View view = localInflater.inflate(R.layout.fragement_personalcenter, container, false);*/



        /*if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/

        ImageView mHBack = (ImageView) view.findViewById(R.id.h_back);
        ImageView mHHead = (ImageView) view.findViewById(R.id.h_head);
        //设置背景磨砂效果
        RequestOptions options = new RequestOptions()
                .transforms(new BlurTransformation(), new CenterCrop());
        Glide.with(getActivity()).load(R.drawable.head)
                .apply(options)
                .into(mHBack);
        //设置圆形图像
        RequestOptions optionss = new RequestOptions()
                .circleCrop();
        Glide.with(getActivity()).load(R.drawable.head)
                .apply(optionss)
                .into(mHHead);


        layout = (BaseItemLayout) view.findViewById(R.id.layout);
        layouttwo = (BaseItemLayout) view.findViewById(R.id.layouttwo);

        List<String> valueList = new ArrayList<>();
        List<String> valueListtwo = new ArrayList<>();


        List<String> rightTextList = new ArrayList<>();

<<<<<<< HEAD
        rightTextList.add("冉旭松");
        rightTextList.add("男");
        rightTextList.add("学生");
        rightTextList.add("who is your daddy?");
=======
        rightTextList.add("暂无");
        rightTextList.add("暂无");
        rightTextList.add(result);  //用户的身份信息
        rightTextList.add("暂无");
>>>>>>> 第三次提交


        valueList.add("用户名");
        valueList.add("性别");
        valueList.add("职业");
        valueList.add("简介");

        valueListtwo.add("修改密码");
        valueListtwo.add("版本");

        List<Integer> resIdList = new ArrayList<>();
        List<Integer> resIdListtwo = new ArrayList<>();

        resIdList.add(R.drawable.user);
        resIdList.add(R.drawable.sex);
        resIdList.add(R.drawable.work);
        resIdList.add(R.drawable.info);
        resIdListtwo.add(R.drawable.password);
        resIdListtwo.add(R.drawable.version);



        ConfigAttrs attrs = new ConfigAttrs();
        ConfigAttrs attrstwo = new ConfigAttrs();

        attrs.setValueList(valueList)  // 文字 list
                .setResIdList(resIdList) // icon list
                .setIconWidth(24)  //设置icon 的大小
                .setIconHeight(24)
                .setItemMarginTop(0)
                .setItemMode(Mode.TEXT)
                .setRightText(rightTextList)
                .setArrowResId(R.drawable.rightarrow)
                .setMarginRight(0);

        layout.setConfigAttrs(attrs)
                .create();

        attrstwo.setValueList(valueListtwo)  // 文字 list
                .setResIdList(resIdListtwo) // icon list
                .setIconWidth(24)  //设置icon 的大小
                .setIconHeight(24)
                .setItemMarginTop(0)
                .setItemMode(Mode.RED_TEXT)
                .setRightText(1,"1")
                .setItemMode(0,Mode.ARROW)
                .setArrowResId(R.drawable.rightarrow)
                .setMarginRight(10);
        layouttwo.setConfigAttrs(attrstwo)
                .create();

        return view;
    }
}
