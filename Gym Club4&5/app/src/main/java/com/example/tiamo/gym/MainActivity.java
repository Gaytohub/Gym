package com.example.tiamo.gym;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomVideoView videoview;
    private Button          btn_enter;
    private Button          btn_entered;
    private TextView        btn_offline;
    private Button          btn_rigester;
    private TextView        noUserRegister;
    private TextView        phonehint;
    private TextView        passwordhint;
    private EditText        nameEdit;
    private EditText        passwordEdit;
    private boolean isVisible = true;
    private LinearLayout layout_1;
    private LinearLayout layout_2;
    private ImageView qq_login;
    private Tencent mTencent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

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
    /**
     * 初始化
     */
    private void initView() {
        nameEdit = (EditText) findViewById(R.id.et_phone);
        passwordEdit = (EditText) findViewById(R.id.et_pwd);
        phonehint =(TextView)findViewById(R.id.m_phone_hint);
        passwordhint =(TextView)findViewById(R.id.m_password_hint);
        layout_1 = (LinearLayout) findViewById(R.id.logined);
        layout_1.setVisibility(View.GONE);
        btn_rigester = (Button) findViewById(R.id.btn_rigester);
        mTencent = Tencent.createInstance("101540020",getApplicationContext());
        qq_login = (ImageView) findViewById(R.id.iv_wechat) ;
        qq_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mTencent.login(MainActivity.this,"all",new BaseUiListener());
            }
        });
        btn_rigester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, dashboard.class);
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

        btn_offline = (TextView) findViewById(R.id.offline);  //离线按钮响应
        btn_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FitnessActivity.class);
                i.putExtra("rejmsg","0");
                startActivity(i);
            }
        });
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

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response){
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            try{
                Log.v("----TAG--", "-------------"+response.toString());
                String openID = ((JSONObject) response).getString("openid");
                mTencent.setOpenId(openID);
                // saveUser("44","text","text",1);
                mTencent.setAccessToken(((JSONObject) response).getString("access_token"),((JSONObject) response).getString("expires_in"));
                Log.v("TAG", "-------------"+openID);
            }catch (JSONException e){
                e.printStackTrace();
            }
            QQToken qqToken = mTencent.getQQToken();
            UserInfo info = new UserInfo(getApplicationContext(), qqToken);

            info.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    try {
                        Log.v("用户名", ((JSONObject) o).getString("nickname"));
                        Log.v("用户姓名", ((JSONObject) o).getString("gender"));
                        Log.v("UserInfo",o.toString());
                        Intent intent1 = new Intent(MainActivity.this,FitnessActivity.class);
                        startActivity(intent1);
                        finish();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(UiError uiError) {
                    Log.v("UserInfo","onError");
                }

                @Override
                public void onCancel() {
                    Log.v("UserInfo","onCancel");
                }
            });
        }

        @Override
        public void onCancel() {
            Log.v("UserInfo","onCancel");
        }

        @Override
        public void onError(UiError uiError) {
            Log.v("UserInfo","onError");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
        if(requestCode == Constants.REQUEST_API) {
            if(resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, new BaseUiListener());
            }
        }
    }
}
