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
import android.os.RemoteException;
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
    public static final int TIME = 5 * 1000;
    public static final int BINDER_CODE = 0x10;

    private HandlerThread mThread;
    private Handler mHandler;

    private String packageName = "com.test.sun.protectservice.protectservice.main";
    private String className = "MainService";
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
                    mHandler.sendEmptyMessageDelayed(MAIN, TIME);
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
            String name = info.service.getClassName();
            if (fullName.equals(name)) {
                Log.i("info", "***************target exists**********************");
                Log.i("info", ":" + info.pid);
                return true;
            }
        }
        Log.i("info", "***************target do not exists**********************");
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("info", TAG + ":onCreate----------------------");
        startCheckThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("info", TAG + ":onStartCommand----------------------");
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
