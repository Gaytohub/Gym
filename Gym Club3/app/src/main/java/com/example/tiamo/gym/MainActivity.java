package com.example.tiamo.gym;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.os.Handler;
import android.os.Message;
>>>>>>> 第三次提交
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
<<<<<<< HEAD
=======
import android.widget.EditText;
>>>>>>> 第三次提交
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

<<<<<<< HEAD
=======
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

>>>>>>> 第三次提交
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomVideoView videoview;
    private Button          btn_enter;
    private Button          btn_entered;
    private Button          btn_rigester;
    private TextView        noUserRegister;
<<<<<<< HEAD
    private boolean isVisible = true;
    private LinearLayout layout_1;
    private LinearLayout layout_2;
=======
    private TextView        phonehint;
    private TextView        passwordhint;
    private EditText        nameEdit;
    private EditText        passwordEdit;
    private boolean isVisible = true;
    private LinearLayout layout_1;
    private LinearLayout layout_2;


>>>>>>> 第三次提交
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

<<<<<<< HEAD
=======
    private int validate(){
        phonehint.setVisibility(View.INVISIBLE);
        passwordhint.setVisibility(View.INVISIBLE);
        String phone = nameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String regExp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if(m.matches()==false){
            phonehint.setVisibility(View.VISIBLE);
            return 0;
        }
        if(password.length()<6){
            passwordhint.setVisibility(View.VISIBLE);
            return 0;
        }
        return 1;
    }
>>>>>>> 第三次提交
    /**
     * 初始化
     */
    private void initView() {
<<<<<<< HEAD
=======
        nameEdit = (EditText) findViewById(R.id.et_phone);
        passwordEdit = (EditText) findViewById(R.id.et_pwd);
        phonehint =(TextView)findViewById(R.id.m_phone_hint);
        passwordhint =(TextView)findViewById(R.id.m_password_hint);
>>>>>>> 第三次提交
        layout_1 = (LinearLayout) findViewById(R.id.logined);
        layout_1.setVisibility(View.GONE);
        btn_rigester = (Button) findViewById(R.id.btn_rigester);
        btn_rigester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
=======
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
>>>>>>> 第三次提交
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

<<<<<<< HEAD
        btn_entered = (Button) findViewById(R.id.btn_entered);
        btn_entered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,FitnessActivity.class);
                startActivity(i);
            }
=======
        btn_entered = (Button) findViewById(R.id.btn_entered);  //登录按钮响应
        btn_entered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "!!!!", Toast.LENGTH_LONG).show();
                int is = validate();
                if(is==1){
                    new Thread(new Runnable() {  //线程进行http
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this, "!!!!", Toast.LENGTH_LONG).show();
                            String url = HttpURLconnection.BASE_URL + "/loginservlet";
                            Map<String, String> params = new HashMap<String, String>();
                            String name = nameEdit.getText().toString();
                            String password = passwordEdit.getText().toString();
                            params.put("name", name);
                            params.put("password", password);

                            String result = HttpURLconnection.getContextByHttp(url, params);

                            Message msg = new Message();
                            msg.what = 0x12;
                            Bundle data = new Bundle();
                            data.putString("result", result);
                            msg.setData(data);


                            hander.sendMessage(msg);
                        }

                        Handler hander = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {

                                if (msg.what == 0x12) {
                                    Bundle data = msg.getData();
                                    String key = data.getString("result");//得到json返回的json
//                                   Toast.makeText(MainActivity.this,key,Toast.LENGTH_LONG).show();
                                    //Toast.makeText(MainActivity.this, "!!!!", Toast.LENGTH_LONG).show();
                                    try {
                                        JSONObject json = new JSONObject(key);
                                        String result = (String) json.get("result");
                                        String[] splited = result.split(" ");
                                        if ("success".equals(splited[0])) {
                                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(MainActivity.this, FitnessActivity.class);
                                            i.putExtra("amsg",splited[1]);
                                            startActivity(i);
                                        } else if ("error".equals(result)) {
                                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        };
                    }).start();
                }



            }

>>>>>>> 第三次提交
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

<<<<<<< HEAD


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



=======
>>>>>>> 第三次提交
}
