package com.test.sun.protectservice.protectservice.assist;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.test.sun.protectservice.protectservice.main.MainService;

import java.util.List;

/**
 * Created by ZS27 on 2016/12/2.
 */

public class AssistService extends Service {


    public static final String TAG = "AssistService";
    public static final int MAIN = 0x100;
    public static final int TIME = 10 * 1000;

    private HandlerThread mThread;
    private Handler mHandler;

    private String packageName = "com.test.sun.protectservice.protectservice.main";
    private String className = "MainService";
    private String fullName = packageName + "." + className;
    private boolean isConnected = false;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("info", TAG + ":onServiceConnected----------------------");
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("info", TAG + ":onServiceDisconnected----------------------");
            isConnected = false;
            startOther();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("info", TAG + ":onCreate----------------------");

        mThread = new HandlerThread("main");
        mThread.start();

        mHandler = new Handler(mThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i("info", "AssistService:handleMessage----------------------");
                startOther();
                mHandler.sendEmptyMessageDelayed(MAIN, TIME);
            }
        };

        mHandler.sendEmptyMessageDelayed(MAIN, TIME);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                while (true) {
//                    try {
//                        Thread.sleep(3 * 1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.i("info", "--AssistService:run--");
//                }
//            }
//        }.start();
        return START_STICKY;
    }

    private void startOther() {
        if (!isAlive(fullName)) {
            Intent intent0 = new Intent();
            intent0.setClassName(getApplicationContext(), fullName);
            startService(intent0);
//            try {
//                Thread.sleep(5 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Intent intent = new Intent();
//            intent.setClassName(getApplicationContext(), fullName);
//            bindService(intent, connection, BIND_AUTO_CREATE);
        }
    }

    public boolean isAlive(String fullName) {
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : services) {
            String name = info.service.getClassName();
            if (fullName.equals(name)) {
                Log.i("info", "***************target exists**********************");
                Log.i("info", ":" + info.pid);
                return true;
            }
        }
        Log.i("info", "***************target dont exists**********************");
        return false;
    }

    @Override
    public void onDestroy() {
        Log.i("info", TAG + ":onDestroy----------------------");
//        unbindService(connection);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("info", TAG + ":onUnbind----------------------");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("info", TAG + ":onBind----------------------");
        return new Binder();
    }

}
