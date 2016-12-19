package com.test.sun.protectservice.root;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/19.
 */

public class Rooted_Receiver extends BroadcastReceiver {


    public static final String TAG = "TestVar";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Rooted_Receiver:onReceive----------------" + intent.getAction());
        Intent intentX = new Intent();
        intentX.setClassName(context, "com.test.sun.protectservice.root.Rooted_Service");
        context.startService(intentX);
    }
}
