package com.test.sun.protectservice.root;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/19.
 */

public class Rooted_Service extends Service {
    public static final String TAG = "TestVar";

    @Override
    public void onCreate() {
        Log.i(TAG, "Rooted_Service:onCreate----------------");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Rooted_Service:onStartCommand----------------");
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("root")
                .setContentText("root")
                .getNotification();
        startForeground(266, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
