package com.test.sun.protectservice.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.test.sun.protectservice.MainActivity;
import com.test.sun.protectservice.R;
import com.test.sun.protectservice.TestService;

/**
 * Created by ZS27 on 2016/12/17.
 */

public class MWidget extends AppWidgetProvider {
    public static final String TAG = "TestVar";
    private long anInt = 10 * 1000;

    public MWidget() {
        super();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "MWidget:onReceive----------------");

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent test_aaa = PendingIntent.getBroadcast(context, 101, new Intent("test_aaa"), PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i(TAG, "setAlarm:start");
            manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + anInt, test_aaa);
        }

        Intent intentX = new Intent();
        intentX.setClassName(context, "com.test.sun.protectservice.TestService");
        context.startService(intentX);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "MWidget:onUpdate----------------" + appWidgetIds.length);

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent test_aaa = PendingIntent.getBroadcast(context, 101, new Intent("test_aaa"), PendingIntent.FLAG_CANCEL_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i(TAG, "setAlarm:start");
            manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + anInt, test_aaa);
        } else {
            Log.i(TAG, "setAlarm:start");
            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + anInt, anInt, test_aaa);
        }

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_liner);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.widget_bt, pi);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i(TAG, "MWidget:onDeleted----------------");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i(TAG, "MWidget:onEnabled----------------");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i(TAG, "MWidget:onDisabled----------------");
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.i(TAG, "MWidget:onRestored----------------");
    }
}
