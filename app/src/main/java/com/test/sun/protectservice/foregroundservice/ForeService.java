package com.test.sun.protectservice.foregroundservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/15.
 */

public class ForeService extends Service {
    public static final String TAG = "ForeService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "ForeService:onCreate----------------");

    }

    /*定制系统gg*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("heihei")
                .setContentText("lalal")
                .getNotification();
        startForeground(266, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(false);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
