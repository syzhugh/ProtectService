package com.test.sun.protectservice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.test.sun.protectservice.root.SysApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TestVar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "MainActivity:onCreate----------------");
        Log.i(TAG, ":hi i am system app!");

        File directory = Environment.getExternalStoragePublicDirectory(AUDIO_SERVICE);
        Log.i(TAG, "--------1");
        if (directory.exists()) {
            Log.i(TAG, "-------0");
            File file = new File(directory, "ddkj.aac");
            if (!file.exists()) {
                try {
                    Log.i(TAG, "-------1");
                    boolean newFile = file.createNewFile();
                    Log.i(TAG, "PublicDirectory:" + newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Log.i(TAG, "-------");
                }
            }
        }

        Log.i(TAG, ":" + Environment.getExternalStorageState());

        File file = new File(Environment.getExternalStorageDirectory(), "ddkj");
        Log.i(TAG, ":" + file.exists());
        if (file.exists()) {
            try {
                File file1 = new File(file, "hiehie1.txt");
                Log.i(TAG, ":" + file1.exists());
                if (!file1.exists()) {
                    Log.i(TAG, "write----0");
                    Log.i(TAG, ":" + file1.createNewFile());
                }
                Log.i(TAG, "write----1");
                PrintWriter writer = new PrintWriter(new File(file, "hiehie.txt"));
                writer.write("hi i am system app!");
                Log.i(TAG, "write----2");
                writer.flush();
                Log.i(TAG, "write----3");
                writer.close();
                Log.i(TAG, ":success to write");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
