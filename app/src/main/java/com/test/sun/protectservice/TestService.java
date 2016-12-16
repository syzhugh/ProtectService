package com.test.sun.protectservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/5.
 */

public class TestService extends Service {

    public static final String TAG = "TestService";

    @Override

    public void onCreate() {
        Log.i(TAG, "TestService:onCreate----------------");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "TestService:onStartCommand----------------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "TestService:onBind----------------");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "TestService:onUnbind----------------");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "TestService:onDestroy----------------");
        super.onDestroy();
    }


}
