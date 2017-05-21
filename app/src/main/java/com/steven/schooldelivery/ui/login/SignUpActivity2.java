package com.steven.schooldelivery.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.steven.schooldelivery.Constant;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.http.HttpLogin;
import com.steven.schooldelivery.http.HttpSignUp;
import com.steven.schooldelivery.http.gson.DataTokens;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.ui.main.MainActivity;
import com.steven.schooldelivery.util.ActivityCollector;
import com.steven.schooldelivery.util.HttpUtil;
import com.steven.schooldelivery.util.LogUtil;

import java.util.HashMap;

public class SignUpActivity2 extends BaseActivity {
    SharedPreferences preferences;

    private EditText name_edit, school_card_edit, school_name_edit, school_address_edit;
    private RadioGroup sex_Group;
    private String mPhone, mPassword, mName, mSchool_card, mSchool_name, mSchool_address;
    private String mSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        initView();
    }

    private void initView() {
        setToolBar("注册");

        name_edit = (EditText) findViewById(R.id.name_edit);
        school_card_edit = (EditText) findViewById(R.id.school_card_edit);
        school_name_edit = (EditText) findViewById(R.id.school_name_edit);
        school_address_edit = (EditText) findViewById(R.id.school_address_edit);
        sex_Group = (RadioGroup) findViewById(R.id.sexGroup);

        preferences = getSharedPreferences(Constant.SharedPreferences.USERINFO, MODE_PRIVATE);
    }

    private void getFormValue() {
        mPhone = getIntent().getStringExtra(Constant.HttpParam.Users.PHONE);
        mPassword =getIntent().getStringExtra(Constant.HttpParam.Users.PASSWORD);

        RadioButton radioButton = (RadioButton) findViewById(sex_Group.getCheckedRadioButtonId());
        mSex = radioButton.getText().toString();

        mName = name_edit.getText().toString();
        mSchool_card = school_card_edit.getText().toString();
        mSchool_name = school_name_edit.getText().toString();
        mSchool_address = school_address_edit.getText().toString();
    }

    public void onClickSignUp(View view) {
        //// TODO: 2017/4/30 格式检查
        getFormValue();
        new SignUpTask().execute();
    }

    private class SignUpTask extends AsyncTask<Void, Void, HttpResponse> {

        @Override
        protected HttpResponse doInBackground(Void... params) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(Constant.HttpParam.Users.PHONE, mPhone);
            hashMap.put(Constant.HttpParam.Users.PASSWORD, mPassword);
            hashMap.put(Constant.HttpParam.Users.SEX, mSex);
            hashMap.put(Constant.HttpParam.Users.NAME, mName);
            hashMap.put(Constant.HttpParam.Users.SCHOLLCARD, mSchool_card);
            hashMap.put(Constant.HttpParam.Users.SCHOOLNAME, mSchool_name);
            hashMap.put(Constant.HttpParam.Users.SCHOOLADDRESS, mSchool_address);

            return new HttpSignUp().send(hashMap);
        }

        @Override
        protected void onPostExecute(HttpResponse httpResponse) {
            if (httpResponse == null) {
                Toast.makeText(SignUpActivity2.this, "网络未连接", Toast.LENGTH_SHORT).show();
                return;
            } else if (httpResponse.getStatus() != 200) {
                Toast.makeText(SignUpActivity2.this, "注册失败\n"+httpResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SignUpActivity2.this, "注册成功", Toast.LENGTH_SHORT).show();
                //保存用户信息
                User user = (User) httpResponse.getData();
                user.save();
                //注册成功后自动登陆
                new AsyncTask<Void, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Void... params) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put(Constant.HttpParam.Tokens.PHONE, mPhone);
                        hashMap.put(Constant.HttpParam.Tokens.PASSWORD, mPassword);
                        //登陆返回结果
                        HttpResponse result = new HttpLogin().send(hashMap);
                        if (result.getStatus() == 200) {  //登陆成功
                            //保存用户登陆信息 与token
                            saveUser((DataTokens) result.getData());
                            return true;
                        } else {
                            return false;
                        }
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        if (aBoolean) {
                            ActivityCollector.finishAll();//关闭登陆 注册页面
                            MainActivity.actionStart(SignUpActivity2.this);
                        } else {             //出现未知异常： 注册成功但不能正常登陆
                            finish();
                        }
                    }
                }.execute();
            }
        }
    }

    /**
     * 保存用户信息到  SharedPreferences 与 数据库
     */
    private void saveUser(DataTokens result) {
        String token = result.getToken();
        LogUtil.d(TAG, "saveUserInfo: token:" + token + "  phone:" + result.getUid() + " password:" + result.getToken());

        HttpUtil.setToken(token);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.SharedPreferences.ID, result.getUid());
        editor.putString(Constant.SharedPreferences.PHONE, mPhone);
        editor.putString(Constant.SharedPreferences.PASSWORD, mPassword);
        editor.apply();
    }

    public static void actionStart(Context context,String phone,String password) {
        Intent intent = new Intent(context, SignUpActivity2.class);
        intent.putExtra(Constant.HttpParam.Users.PHONE,phone);
        intent.putExtra(Constant.HttpParam.Users.PASSWORD,password);
        context.startActivity(intent);
    }
}
