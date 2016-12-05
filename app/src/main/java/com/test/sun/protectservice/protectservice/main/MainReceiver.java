package com.test.sun.protectservice.protectservice.main;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/2.
 */

public class MainReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("info", "MainReceiver:onReceive----------------------");
        Intent intent1 = new Intent();
        intent1.setClassName(context, "com.test.sun.protectservice.protectservice.main.MainService");
        context.startService(intent1);
    }
}
