package com.test.sun.protectservice.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/10.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TAG = "TestVar";

    public static final int PI_REQUESTCODE = 0x10;
    public static final int MINUTE = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "AudioReceiver:onReceive----------------" + intent.getAction());
        /*用于不版本的闹铃设置*/
        setAlarm(context);
        /*广播接收后，进行相关操作*/
        startMyService(context);
    }

    private void setAlarm(Context context) {
        Log.i(TAG, "AlarmReceiver:setAlarm----------------");
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intentX = new Intent("audio_receiver");
//        PendingIntent pi = PendingIntent.getBroadcast(context, PI_REQUESTCODE, intentX, PendingIntent.FLAG_CANCEL_CURRENT);
        Intent intentX = new Intent();
        intentX.setClassName(context, "com.test.sun.protectservice.TestService");
        PendingIntent pi = PendingIntent.getService(context, PI_REQUESTCODE, intentX, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i(TAG, "setAlarm:start");
            manager.setExact(AlarmManager.RTC_WAKEUP, getTriggerTime(), pi);
        } else {
            Log.i(TAG, "setAlarm:start");
            manager.setRepeating(AlarmManager.RTC_WAKEUP, getTriggerTime(), getIntervalTime(), pi);
        }
    }

    private long getTriggerTime() {
        return System.currentTimeMillis() + getIntervalTime();
    }

    private long getIntervalTime() {
        return MINUTE * 60 * 1000;
    }

    private void startMyService(Context context) {
        Intent intentX = new Intent();
        intentX.setClassName(context, "com.test.sun.protectservice.TestService");
        context.startService(intentX);
    }
}
