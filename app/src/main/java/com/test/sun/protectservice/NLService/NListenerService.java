package com.test.sun.protectservice.NLService;

import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/21.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NListenerService extends NotificationListenerService {

    public static final String TAG = "TestVar";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "NListenerService:onCreate----------------");
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.i(TAG, "NListenerService:onListenerConnected----------------");
    }

    @Override
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
        Log.i(TAG, "NListenerService:onListenerDisconnected----------------");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
    }

}
