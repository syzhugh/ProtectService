package com.test.sun.protectservice.protectservice.assist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/2.
 */

public class AssistReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("info", "AssistReceiver:onReceive----------------------");
        Intent intent1 = new Intent(context, AssistService.class);
        intent1.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        context.startService(intent1);
    }
}
