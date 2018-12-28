package com.example.tiamo.gym;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Page3 extends Fragment {
    private RecyclerView mRecyclerView = null;
    private View view;
    private String[] dataset = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //获取fragment的layout
        view = inflater.inflate(R.layout.recycler_view, container, false);
        //对recyclerview进行配置
        initEvent();
        return view;
    }

    private void initViews(){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_recyclerView);
    }

    private void initEvent() {
        initViews();
        initData();
        setViews();
    }


    private void initData(){
        dataset = new String[8];
    }

    private void setViews(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(dataset,getActivity());
        mRecyclerView.setAdapter(adapter);
    }
}