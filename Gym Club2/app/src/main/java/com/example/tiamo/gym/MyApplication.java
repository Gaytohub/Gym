package com.example.tiamo.gym;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this.getApplicationContext();
    }

    public static Context getContext() {
        return appContext;
    }

}
