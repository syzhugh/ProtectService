package com.test.sun.protectservice.NLService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/21.
 */

public class NLSReceiver extends BroadcastReceiver {
    public static final String TAG = "TestVar";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "NLSReceiver:onReceive----------------");
    }
}
