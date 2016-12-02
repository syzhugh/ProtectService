package com.test.sun.protectservice.protectservice;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/2.
 */

public class MApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("info", "MApp:onCreate----------------------");
        Log.i("info", ":" + Process.myPid());
    }

    public Context getAA() {
        return getApplicationContext();
    }
}
