package com.test.sun.protectservice.root;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.test.sun.protectservice.utils.CheckUtils;

/**
 * Created by ZS27 on 2016/12/19.
 */

public class Rooted_Receiver extends BroadcastReceiver {

    public static final int anInt = 20 * 1000;
    public static final String TAG = "TestVar";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "Rooted_Receiver:onReceive----------------" + action);

        if (action.equals("simulateBOOT")) {
            Intent intentX = new Intent();
            intentX.setClassName(context, "com.test.sun.protectservice.root.Rooted_Service");
            context.startService(intentX);
        }


        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent rooted = PendingIntent.getBroadcast(context, 501, new Intent("rooted"), PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + anInt, rooted);
        } else {
            manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + anInt, rooted);
        }

        if (action.equals("rooted")) {
            /*检查开启服务*/
            Intent intentX = new Intent();
            intentX.setClassName(context, "com.ddcrm.service.PhoneService");
            intentX.addCategory(Intent.CATEGORY_DEFAULT);
            context.startService(intentX);

            if (!CheckUtils.checkServiceIsAlive(context, "com.ddcrm.service.PhoneService")) {
            }
        }
    }
}
