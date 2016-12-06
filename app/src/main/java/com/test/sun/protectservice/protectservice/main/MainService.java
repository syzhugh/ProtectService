package com.test.sun.protectservice.protectservice.main;

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
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.test.sun.protectservice.protectservice.assist.AssistService;

import java.util.List;

public class MainService extends Service {

    public static final String TAG = "MainService";
    public static final int MAIN = 0x100;
    public static final int TIME = 500;
    public static final int BINDER_CODE = 0x10;

    private HandlerThread mThread;
    private Handler mHandler;

    private String packageName = "com.test.sun.protectservice.protectservice.assist";
    private String className = "AssistService";
    private String fullName = packageName + "." + className;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("info", TAG + ":onServiceConnected----------------------");
            stopCheckThread();
            try {
                iBinder.linkToDeath(new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        Log.i("info", TAG + ":binderDied----------------------");
                        reconnect();
                    }
                }, BINDER_CODE);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("info", TAG + ":onServiceDisconnected----------------------");
        }
    };

    private void reconnect() {
        Log.i("info", TAG + ":reconnect----------------------");
        if (isAlive(fullName)) {
            bindOther();
        } else {
            startCheckThread();
        }
    }

    private void stopCheckThread() {
        Log.i("info", TAG + ":stopCheckThread----------------------");
        if (mHandler != null) {
            mHandler.removeMessages(MAIN);
            if (mThread != null) {
                mThread.quit();
                mHandler = null;
                mThread = null;
            }
        }
    }

    private void startCheckThread() {
        Log.i("info", TAG + ":startCheckThread----------------------");
        mThread = new HandlerThread("main");
        mThread.start();
        mHandler = new Handler(mThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i("info", TAG + ":handleMessage----------------------");
                startOther();
                if (isAlive(fullName)) {
                    bindOther();
                } else {
//                    mHandler.sendEmptyMessageDelayed(MAIN, TIME);
                    mHandler.sendEmptyMessage(MAIN);
                }
            }
        };
        mHandler.sendEmptyMessage(MAIN);
    }


    private void bindOther() {
        Log.i("info", TAG + ":bindOther----------------------");
        Intent intent0 = new Intent();
        intent0.setClassName(getApplicationContext(), fullName);
        bindService(intent0, connection, BIND_AUTO_CREATE);
    }


    private void startOther() {
        Log.i("info", TAG + ":startOther----------------------");
        if (!isAlive(fullName)) {
            Intent intent0 = new Intent();
            intent0.setClassName(getApplicationContext(), fullName);
            startService(intent0);
        }
    }

    public boolean isAlive(String fullName) {
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : services) {
            Log.i("info", ":" + info.service.getShortClassName());
            if (fullName.equals(info.service.getClassName()) && info.pid != 0) {
                Log.i("info", "***************target exists**********************");
                Log.i("info", ":" + info.pid);
                Log.i("info", ":" + info.started);
                return true;
            }
        }
        Log.i("info", "***************target do not exists**********************");
        return false;
    }

    public static final String APP_ID = "2882303761517530544";
    public static final String APP_KEY = "5811753074544";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("info", TAG + ":onCreate----------------------");
        startCheckThread();


        initMiPush();
    }

    private void initMiPush() {
        Log.i("info", "MainService:initMiPush----------------------");
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
//            Log.i("info", " pid: " + info.pid + " processName: " + info.processName);
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("info", TAG + ":onStartCommand----------------------");

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Log.i("print", "MainService:run----------------------");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("info", TAG + ":onDestroy----------------------");
        unbindService(connection);
        super.onDestroy();
        stopCheckThread();
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
