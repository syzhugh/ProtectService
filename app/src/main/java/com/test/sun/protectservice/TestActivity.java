package com.test.sun.protectservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by ZS27 on 2016/12/17.
 */

public class TestActivity extends AppCompatActivity {
    public static final String TAG = "MWidget";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "TestActivity:onCreate----------------");
    }
}
