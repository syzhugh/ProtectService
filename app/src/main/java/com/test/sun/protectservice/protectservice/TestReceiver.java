package com.test.sun.protectservice.protectservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/5.
 */

public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("info", "TestReceiver:onReceive----------------------");
        Log.i("info", ":" + intent.getAction());
    }
}
