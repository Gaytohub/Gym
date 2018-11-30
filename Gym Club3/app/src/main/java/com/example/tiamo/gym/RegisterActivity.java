package com.example.tiamo.gym;

<<<<<<< HEAD
import android.content.Intent;
=======
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
>>>>>>> 第三次提交
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageView;
=======
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
>>>>>>> 第三次提交

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.angmarch.views.NiceSpinner;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
=======
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class RegisterActivity extends AppCompatActivity{

    private Button registerButton;
    private Button videobutton;
    private EditText nameEdit;
    private EditText passwordEdit;
    private EditText repasswordEdit;
    private VideoView mVideoView;
    private int typeindex;
    private Uri uri;

    private String[] video = {
            "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8"
    };

    private int validate(){
        String phone = nameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String repassword = repasswordEdit.getText().toString();
        String regExp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if(m.matches()==false){
            Toast.makeText(RegisterActivity.this, "输入正确的手机号", Toast.LENGTH_LONG).show();
            return 0;
        }
        if(password.length()<6){
            Toast.makeText(RegisterActivity.this, "密码不能少于6位", Toast.LENGTH_LONG).show();
            return 0;
        }
        if(!password.equals(repassword)){
            Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_LONG).show();
            return 0;
        }
        return 1;
    }

>>>>>>> 第三次提交

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
<<<<<<< HEAD
=======


>>>>>>> 第三次提交
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
<<<<<<< HEAD
        NiceSpinner niceSpinner = (NiceSpinner)findViewById(R.id.nice_spinner);
=======
        final  NiceSpinner niceSpinner = (NiceSpinner)findViewById(R.id.nice_spinner);
>>>>>>> 第三次提交
        List<String> dataList = new ArrayList<>();
        dataList.add("学生");
        dataList.add("教练");
        dataList.add("上班族");
        dataList.add("老师");

<<<<<<< HEAD

        niceSpinner.attachDataSource(dataList);

        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

=======
        //videobutton = (Button) findViewById(R.id.testvideo);
        nameEdit = (EditText) findViewById(R.id.et_phone);
        passwordEdit = (EditText) findViewById(R.id.et_pwd);
        repasswordEdit = (EditText) findViewById(R.id.et_repwd);

        niceSpinner.attachDataSource(dataList);


        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeindex = niceSpinner.getSelectedIndex();
>>>>>>> 第三次提交
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



<<<<<<< HEAD
        registerButton = (Button) findViewById(R.id.btn_entered);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,FitnessActivity.class);
                startActivity(i);
            }
=======


//        videobutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //设置监听
//                Intent i = new Intent(RegisterActivity.this, video.class);
//                //i.putExtra("amsg",splited[1]);
//                startActivity(i);
//
//            }
//        });



        registerButton = (Button) findViewById(R.id.btn_entered);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "!!!!", Toast.LENGTH_LONG).show();
                int is = validate();
                if(is==1) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(MainActivity.this, "!!!!", Toast.LENGTH_LONG).show();
                            String url = HttpURLconnection.BASE_URL + "/registerservlet";
                            Map<String, String> params = new HashMap<String, String>();
                            String name = nameEdit.getText().toString();
                            String password = passwordEdit.getText().toString();
                            String type="";
                            params.put("name", name);
                            params.put("password", password);
                            switch (typeindex){
                                case 0:
                                    type="student";
                                    break;
                                case 1:
                                    type="coach";
                                    break;
                                case 2:
                                    type="worker";
                                    break;
                                default:
                                    type="teacher";
                                    break;
                            }
                            params.put("type", type);

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
                                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(RegisterActivity.this, FitnessActivity.class);
                                            i.putExtra("amsg",splited[1]);
                                            startActivity(i);
                                        } else if ("error".equals(result)) {
                                            Toast.makeText(RegisterActivity.this, "用户名存在", Toast.LENGTH_LONG).show();
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
    }


<<<<<<< HEAD

=======
>>>>>>> 第三次提交
}
