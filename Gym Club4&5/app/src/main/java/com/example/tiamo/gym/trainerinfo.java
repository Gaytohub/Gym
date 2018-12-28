package com.example.tiamo.gym;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class trainerinfo extends AppCompatActivity {

    public static final Uri TRRAINER_INFO = Uri.parse("content://myprovider/table_trainerinfo");
    private String trainername;
    private TextView tname;
    private TextView tage;
    private TextView tsex;
    private TextView tinfo;


    public void offlineread(){
        Log.d("trainerinfo", "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        Cursor cursor = getContentResolver().query(TRRAINER_INFO, new String[]{"name","age","sex","info"}, "name=?",new String[]{"frank"}, null, null);
        if(cursor.moveToFirst()){
            do{
                System.out.println("the answer is: "+cursor.getString(0)+" "+cursor.getString(1));
                tname.setText("name: "+cursor.getString(0));
                tage.setText("age: "+cursor.getString(1));
                tsex.setText("sex: "+cursor.getString(2));
                tinfo.setText("info: "+cursor.getString(3));
            } while(cursor.moveToNext());
        }
        cursor.close();


    }


    public void getinfo(){
        new Thread(new Runnable() {  //线程进行http
            @Override
            public void run() {
                //Toast.makeText(MainActivity.this, "!!!!", Toast.LENGTH_LONG).show();
                String url = HttpURLconnection.BASE_URL + "/Trainer";
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", trainername);
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
                        Log.d("trainerinfo", "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                        Log.d("trainerinfo", key);
//                                   Toast.makeText(MainActivity.this,key,Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, "!!!!", Toast.LENGTH_LONG).show();
                        try {
                            JSONObject json = new JSONObject(key);
                            String result = (String) json.get("result");
                            Log.d("trainerinfo", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                            Log.d("trainerinfo", result);
                            String[] splited = result.split("!");
                            tname.setText("name: "+splited[0]);
                            tage.setText("age: "+splited[1]);
                            tsex.setText("sex: "+splited[2]);
                            tinfo.setText("info: "+splited[3]);
                            ContentValues contentValues=new ContentValues();
                            contentValues.put("name",splited[0]);
                            contentValues.put("age",splited[1]);
                            contentValues.put("sex",splited[2]);
                            contentValues.put("info",splited[3]);
                            getContentResolver().insert(TRRAINER_INFO,contentValues);
                        } catch (JSONException e) {
                            offlineread();
                        }
                    }
                }
            };
        }).start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainerinfo);

        Intent intent = getIntent();//获取传来的intent对象
        trainername = intent.getStringExtra("namemsg");//获取键值对的键名

        tname = (TextView)findViewById(R.id.tname);
        tage = (TextView)findViewById(R.id.tage);
        tsex = (TextView)findViewById(R.id.tsex);
        tinfo = (TextView)findViewById(R.id.info1);
        getinfo();

//        getContentResolver().delete(TRRAINER_INFO,"name=?",new String[]{"frank"});
//        getContentResolver().delete(TRRAINER_INFO,"name=?",new String[]{"haowei"});
//        getContentResolver().delete(TRRAINER_INFO,"name=?",new String[]{"song"});
//        getContentResolver().delete(TRRAINER_INFO,"name=?",new String[]{"xuefeiyue"});

    }
}
