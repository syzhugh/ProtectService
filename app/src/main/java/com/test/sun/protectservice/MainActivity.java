package com.test.sun.protectservice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.test.sun.protectservice.utils.SysApp;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TestVar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test1();
        String command = "chmod 777 " + getPackageCodePath();
        boolean b = SysApp.RootCommand(command);
        if (b) {
            Log.i(TAG, "RootCommand:true");
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ddkj/ddkj.apk");
            if (file.exists()) {
                Log.i(TAG, "file:true");
                boolean b1 = SysApp.writeToSystemApp(file);
                Log.i(TAG, "file:" + b1);
            }
        }
    }

    private void test1() {
        int i = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.i(TAG, "checkSelfPermission:" + (i == PackageManager.PERMISSION_GRANTED));
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);


        Log.i(TAG, "MainActivity:onCreate----------------");
        Log.i(TAG, ":hi i am system app!");

        File dir = Environment.getExternalStorageDirectory();
        Log.i(TAG, "getExternalStorageDirectory:" + createFile(dir));

        File dir1 = Environment.getExternalStoragePublicDirectory(AUDIO_SERVICE);
        Log.i(TAG, "getExternalStoragePublicDirectory:" + createFile(dir1));

        Log.i(TAG, "getFilesDir:" + createFile(getFilesDir()));

        Log.i(TAG, "getExternalFilesDir:" + createFile(getExternalFilesDir(ACCESSIBILITY_SERVICE)));
    }

    public boolean createFile(File parent) {
        Log.i(TAG, "parent::" + parent.getAbsolutePath());
        File file1 = new File(parent, "123");
        if (!file1.exists()) {
            Log.i(TAG, "exists:no");
            return file1.mkdir();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "Granted:" + PackageManager.PERMISSION_GRANTED);
        for (int i = 0; i < permissions.length; i++) {
            Log.i(TAG, ":" + permissions[i] + " " + grantResults[i]);
        }
    }
}
