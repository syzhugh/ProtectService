package com.test.sun.protectservice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startService();

        startForeService();
    }

    private void startForeService() {
        Intent intentX = new Intent();
        intentX.setClassName(MainActivity.this, "com.test.sun.protectservice.foregroundservice.ForeService");
        startService(intentX);
    }

    private void startService() {
        Intent intent0 = new Intent();
        intent0.setComponent(new ComponentName(MainActivity.this, "com.test.sun.protectservice.protectservice.assist.AssistService"));
        startService(intent0);
    }


    private void testBindAndStart() {
        Intent intent0 = new Intent();
        intent0.setClassName(getApplicationContext(), "com.test.sun.protectservice.TestService");
//        startService(intent0);
        bindService(intent0, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, BIND_AUTO_CREATE);
    }

}
