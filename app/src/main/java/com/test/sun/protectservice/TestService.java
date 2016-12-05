package com.test.sun.protectservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/5.
 */

public class TestService extends Service {
    @Override
    public void onCreate() {
        Log.i("info", "TestService:onCreate----------------------");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("info", "TestService:onDestroy----------------------");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("info", "TestService:onUnbind----------------------");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("info", "TestService:onBind----------------------");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("info", "TestService:onStartCommand----------------------");
        return super.onStartCommand(intent, flags, startId);
    }
}
