package com.test.sun.protectservice.root;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/19.
 */

public class Rooted_Service extends Service {
    public static final String TAG = "TestVar";
    public static final int anInt = 20 * 1000;

    @Override
    public void onCreate() {
        Log.i(TAG, "Rooted_Service:onCreate----------------");
        super.onCreate();

        AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent rooted = PendingIntent.getBroadcast(getApplicationContext(), 501, new Intent("rooted"), PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i(TAG, "setAlarm:start");
            manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + anInt, rooted);
        } else {
            Log.i(TAG, "setAlarm:start");
            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + anInt, anInt, rooted);
        }

        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.ddcrm.service.PhoneService");
        startService(launchIntentForPackage);
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
