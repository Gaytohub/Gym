package com.example.tiamo.gym;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Page1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
<<<<<<< HEAD
        View view = inflater.inflate(R.layout.fragment_page1, container, false);
=======
        View view = inflater.inflate(R.layout.sliding_switch, container, false);
>>>>>>> 第三次提交
        return view;
    }
}