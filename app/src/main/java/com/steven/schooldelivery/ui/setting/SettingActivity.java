package com.steven.schooldelivery.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.steven.schooldelivery.Constant;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.ui.login.LoginActivity;
import com.steven.schooldelivery.util.ActivityCollector;
import com.steven.schooldelivery.util.HttpUtil;
import com.steven.schooldelivery.util.LogUtil;

public class SettingActivity extends BaseActivity {
    private static final String TAG = "SettingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    /**
     * 点击退出登陆
     */
    public void onClickExit(View view) {
        clearUserInfo();
        ActivityCollector.finishAll();
        LoginActivity.actionStart(this);
    }

    /**
     * 删除用户token与sharedPreferences信息
     */
    private void clearUserInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.SharedPreferences.USERINFO,MODE_PRIVATE);
        LogUtil.d(TAG, "clearUserInfo");

        HttpUtil.setToken("");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.SharedPreferences.PHONE, "");
        editor.putString(Constant.SharedPreferences.PASSWORD, "");
        editor.putString(Constant.SharedPreferences.ID, "");
        editor.apply();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
