package com.example.tiamo.gym;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomVideoView videoview;
    private Button          btn_enter;
    private Button          btn_entered;
    private Button          btn_rigester;
    private TextView        noUserRegister;
    private boolean isVisible = true;
    private LinearLayout layout_1;
    private LinearLayout layout_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        layout_1 = (LinearLayout) findViewById(R.id.logined);
        layout_1.setVisibility(View.GONE);
        btn_rigester = (Button) findViewById(R.id.btn_rigester);
        btn_rigester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        btn_enter = (Button) findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisible) {
                    isVisible = false;
                    layout_1.setVisibility(View.VISIBLE);//这一句显示布局LinearLayout区域
                    layout_2 = (LinearLayout) findViewById(R.id.login);
                    layout_2.setVisibility(View.GONE);
                } else {
                    layout_1.setVisibility(View.GONE);//这一句即隐藏布局LinearLayout区域
                    isVisible = true;
                }
            }
        });

        btn_entered = (Button) findViewById(R.id.btn_entered);
        btn_entered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,FitnessActivity.class);
                startActivity(i);
            }
        });
        /*videoview = (CustomVideoView) findViewById(R.id.videoview);
        videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sport));

        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });*/


        noUserRegister = (TextView) findViewById(R.id.tv_register);
        noUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        ImageView mGif = (ImageView) findViewById(R.id.gif);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(R.drawable.sports)
                .apply(options)
                .into(mGif);


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



//
//    //返回重启加载
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        initView();
//    }
//
//    //防止锁屏或者切出的时候，音乐在播放
//    @Override
//    protected void onStop() {
//        super.onStop();
//        videoview.stopPlayback();
//    }



}
