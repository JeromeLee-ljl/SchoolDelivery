package com.steven.schooldelivery.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.util.ActivityCollector;
import com.steven.schooldelivery.util.LogUtil;

/**
 * Created by 22340 on 2017/4/5.
 */

public class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    protected int primaryColor;
    protected int white;

    protected ActionBar actionBar;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName());
        ActivityCollector.addActivity(this);

        init();
    }


    private void init() {
        primaryColor = getResources().getColor(R.color.primary);
        white = getResources().getColor(R.color.white);

        setStatusBarColor();
    }


    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    /**
     * 设置toolbar标题
     */
    protected void setToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) return;

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, getClass().getSimpleName() + " --> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, getClass().getSimpleName() + " --> onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG, getClass().getSimpleName() + " --> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG, getClass().getSimpleName() + " --> onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG, getClass().getSimpleName() + " --> onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, getClass().getSimpleName() + " --> onDestroy");
        ActivityCollector.removeActivity(this);
    }
}
