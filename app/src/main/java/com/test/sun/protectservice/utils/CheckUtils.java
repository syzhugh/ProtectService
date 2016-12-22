package com.test.sun.protectservice.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.List;

/**
 * Created by ZS27 on 2016/12/12.
 */

public class CheckUtils {

    public static final String TAG = "TestVar";

    public static boolean checkServiceIsAlive(Context context, String componentname) {
        //Log.i(TAG, "CheckUtils:checkServiceIsAlive----------------");
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(50);
        for (ActivityManager.RunningServiceInfo runningService : runningServices) {
            if (runningService.service.getClassName().equals(componentname) && runningService.pid != 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkActIsTop(Context context, String componentname) {
        //Log.i(TAG, "UploadBroadcast:checkUserAct----------------");
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager.getRunningTasks(1).get(0).topActivity.getClassName().equals(componentname)) {
            return true;
        } else {
            return false;
        }
//      显示所有打开的app的顶端activity
//      当前显示的act永远在第一位
//        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(100);
//        for (ActivityManager.RunningTaskInfo runningTask : runningTasks) {
//            //Log.i(TAG, ":" + runningTask.topActivity.getClassName());
//        }
    }

    public static boolean checkNetWork(Context context) {
        //Log.i(TAG, "UploadBroadcast:checkWifi----------------");
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] allNetworkInfo = manager.getAllNetworkInfo();
        for (NetworkInfo info : allNetworkInfo) {
            if (info.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkWifi(Context context) {
        //Log.i(TAG, "UploadBroadcast:checkWifi----------------");
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


}
