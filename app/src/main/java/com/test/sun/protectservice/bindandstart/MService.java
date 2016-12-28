package com.test.sun.protectservice.bindandstart;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/24.
 */

public class MService extends Service {
    public static final String TAG = "TestVar";

    @Override
    public void onCreate() {
        Log.i(TAG, "MService:onCreate----------------");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MService:onStartCommand----------------");
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MService:onDestroy----------------");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "MService:onUnbind----------------");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "MService:onBind----------------");
        return new Binder();
    }
}
