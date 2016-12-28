package com.test.sun.protectservice.widget;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/19.
 */

public class MAssistReceiver extends BroadcastReceiver {
    public static final String TAG = "TestVar";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "MAssistReceiver:onReceive----------------" + intent.getAction());

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intentX = new Intent("test_aaa");
            context.sendBroadcast(intentX);
        }

    }
}
