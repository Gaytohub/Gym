package com.example.tiamo.gym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;

    public  void changePicture(int i){
        if(i==1){
            ImageView mExpert = (ImageView) findViewById(R.id.expert);
            ImageView mFreshman = (ImageView) findViewById(R.id.freshman);
            RequestOptions options = new RequestOptions()
                    .circleCrop();
            Glide.with(this).load(R.drawable.test3)
                    .apply(options)
                    .into(mExpert);
            RequestOptions optionss = new RequestOptions()
                    .circleCrop();
            Glide.with(this).load(R.drawable.test1)
                    .apply(optionss)
                    .into(mFreshman);
        }
        else{
            ImageView mExpert = (ImageView) findViewById(R.id.expert);
            ImageView mFreshman = (ImageView) findViewById(R.id.freshman);
            RequestOptions options = new RequestOptions()
                    .circleCrop();
            Glide.with(this).load(R.drawable.test4)
                    .apply(options)
                    .into(mExpert);
            RequestOptions optionss = new RequestOptions()
                    .circleCrop();
            Glide.with(this).load(R.drawable.test2)
                    .apply(optionss)
                    .into(mFreshman);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        NiceSpinner niceSpinner = (NiceSpinner)findViewById(R.id.nice_spinner);
        List<String> dataList = new ArrayList<>();
        dataList.add("学生");
        dataList.add("教练");
        dataList.add("上班族");
        dataList.add("老师");


        niceSpinner.attachDataSource(dataList);

        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        ImageView mExpert = (ImageView) findViewById(R.id.expert);
        ImageView mFreshman = (ImageView) findViewById(R.id.freshman);
        RequestOptions options = new RequestOptions()
                .circleCrop();
        Glide.with(this).load(R.drawable.test4)
                .apply(options)
                .into(mExpert);
        RequestOptions optionss = new RequestOptions()
                .circleCrop();
        Glide.with(this).load(R.drawable.test1)
                .apply(optionss)
                .into(mFreshman);

        mExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture(1);
            }
        });

        mFreshman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture(2);
            }
        });



        registerButton = (Button) findViewById(R.id.btn_entered);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,FitnessActivity.class);
                startActivity(i);
            }
        });
    }



}
