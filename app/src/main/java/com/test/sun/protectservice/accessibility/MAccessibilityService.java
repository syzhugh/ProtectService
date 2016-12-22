package com.test.sun.protectservice.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.app.Notification;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ZS27 on 2016/12/10.
 */

public class MAccessibilityService extends AccessibilityService {
    public static final String TAG = "TestVar";

    public static final int CHECK_CODE = 0x10;
    public static final int INTERVAL_TIME = 30 * 1000;
    private HandlerThread handlerThread;
    private Handler handler;

    private Set<String> services;

    @Override
    public void onCreate() {
        Log.i(TAG, "MAccessibilityService:onCreate----------------");
        Log.i(TAG, ":" + Process.myPid());
        super.onCreate();

        initCheckThread();
        initServiceSet();
    }

    private void initCheckThread() {
        handlerThread = new HandlerThread("MAccessibilityService_checkthread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                checkServices();
                this.sendEmptyMessageDelayed(CHECK_CODE, INTERVAL_TIME);
            }
        };
        handler.sendEmptyMessage(CHECK_CODE);
    }

    private void initServiceSet() {
        services = new HashSet<>();
        services.add("com.ddcrm.service.LocationService");
        services.add("com.ddcrm.service.LocationStartService");
        services.add("com.ddcrm.service.SendCallLogService");
        services.add("com.ddcrm.service.PhoneService");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(TAG, "getPackageName////" + event.getPackageName());
        Log.i(TAG, "getEventType////" + AccessibilityEvent.eventTypeToString(event.getEventType()));
        Log.i(TAG, "getAction////" + event.getAction());
    }

    private void checkServices() {
        Log.i(TAG, "MAccessibilityService:checkServices----------------");
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(100);
        HashSet<String> needStart = new HashSet<>(services);
        for (ActivityManager.RunningServiceInfo runningService : runningServices) {
            if (needStart.contains(runningService.service.getClassName()) && runningService.pid != 0) {
                needStart.remove(runningService.service.getClassName());
            }
        }
        Log.i(TAG, "num:" + needStart.size());
        if (needStart.size() > 0) {
            Iterator<String> iterator = needStart.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                toStart(next);
            }
        }
    }

    private void toStart(String next) {
        Log.i(TAG, "need to start:" + next);
        Intent intentX = new Intent();
        intentX.setClassName(getApplicationContext(), next);
        startService(intentX);
    }

    @Override
    public void onInterrupt() {
        Log.i(TAG, "MAccessibilityService:onInterrupt----------------");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MAccessibilityService:onDestroy----------------");
        super.onDestroy();
    }
}

