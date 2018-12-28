package com.example.tiamo.gym;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.example.tiamo.gym.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import static io.vov.vitamio.MediaPlayer.VIDEOQUALITY_HIGH;

public class video extends AppCompatActivity {

    String[] urlist = new String[]{"http://192.168.0.239:10080/fvod/Zr1UvdLig/video.m3u8",
            "http://192.168.0.239:10080/fvod/7EhW5dLmR/video.m3u8",
            "http://192.168.0.239:10080/fvod/IXbW5dYig/video.m3u8",
            "http://192.168.0.239:10080/fvod/3NPZ5dYmR/video.m3u8",
            "http://192.168.0.239:10080/fvod/GE8WcdLmg/video.m3u8",
            "http://192.168.0.239:10080/fvod/tEXZcdLmg/video.m3u8",
            "http://192.168.0.239:10080/fvod/gNqZ5OYiR/video.m3u8",
            "http://192.168.0.239:10080/fvod/4aiG5OYmg/video.m3u8",
            "http://192.168.0.239:10080/fvod/jOIGcdYiR/video.m3u8",
            "http://192.168.0.239:10080/fvod/SfoGcdYig/video.m3u8",
            "http://192.168.0.239:10080/fvod/FByGcdYig/video.m3u8",
            "http://192.168.0.239:10080/fvod/Q3gn5dYig/video.m3u8",
            "http://192.168.0.239:10080/fvod/brn7cOLmR/video.m3u8",
            "http://192.168.0.239:10080/fvod/sadncdLig/video.m3u8",
            "http://192.168.0.239:10080/fvod/kC2n5dLig/video.m3u8"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //初始化 Vitamio.initialize();

        Vitamio.initialize(getApplicationContext());
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        Intent intent = getIntent();//获取传来的intent对象
        String data = intent.getStringExtra("videolist");//获取键值对的键名
        int num = Integer.parseInt(data);

            //之后的设置 和google 官方提供的VideoView的配置差不多
            VideoView videoView = (VideoView) findViewById(R.id.videoView);
            videoView.setVideoURI(Uri.parse(urlist[num-1]));
            //使用类库提供的MediaController (注意是Vitamio包下的)
            MediaController controller = new MediaController(this);
            //双重绑定
            videoView.setMediaController(controller);
            controller.setMediaPlayer(videoView);
            videoView.setVideoQuality(VIDEOQUALITY_HIGH);
            //播放视频
            videoView.start();

    }
}
