package com.steven.schooldelivery.ui.login;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.Constant;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.http.HttpGetUsersInfo;
import com.steven.schooldelivery.http.HttpLogin;
import com.steven.schooldelivery.http.gson.DataTokens;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.ui.main.MainActivity;
import com.steven.schooldelivery.util.DensityUtils;
import com.steven.schooldelivery.util.HttpUtil;
import com.steven.schooldelivery.util.LogUtil;

import org.litepal.tablemanager.Connector;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {

    private ImageView app_imageV;
    private EditText phone_edit;
    private EditText password_edit;
    private LinearLayout login_form_layout;
    private TextView sign_up_textV;

    SharedPreferences preferences;

    private String mPhone = "";
    private String mPassword = "";

    //// TODO: 2017/4/23 delete
    private String testPass = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        attemptToLogin();
    }

    private void attemptToLogin() {
        mPhone = preferences.getString(Constant.SharedPreferences.PHONE, "");
        mPassword = preferences.getString(Constant.SharedPreferences.PASSWORD, "");

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isOpenMainActivity;
            if ("".equals(mPhone) || "".equals(mPassword)) {  //SharedPreferences 没有记录
                isOpenMainActivity = false;
            } else {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Constant.HttpParam.Tokens.PHONE, mPhone);
                hashMap.put(Constant.HttpParam.Tokens.PASSWORD, mPassword);
                //登陆返回结果
                HttpResponse result = new HttpLogin().send(hashMap);//网络不通或网址不对时 返回null

                if (result == null) {                                //网络不通时，使之进入app能查看历史信息
                    isOpenMainActivity = true;
                } else if (result.getStatus() == 200) {                   //登陆成功
                    isOpenMainActivity = true;

                    saveUserInfo((DataTokens) result.getData());        //保存用户信息 与token 用户信息 到数据库

                } else {
                    isOpenMainActivity = false;
                }
            }
            boolean finalIsOpenMainActivity = isOpenMainActivity;
            runOnUiThread(() -> {
                if (finalIsOpenMainActivity) {                  //进入主界面
                    MainActivity.actionStart(LoginActivity.this);
                    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    finish();
                } else {                                        //显示表单
                    showLoginForm();
                }
            });
        }).start();
    }

    // private class AttemptToLoginTask extends AsyncTask<Void, Void, Boolean> {
    //     /**
    //      * 通过 SharedPreferences 登陆
    //      */
    //     @Override
    //     protected Boolean doInBackground(Void... params) {
    //         //模拟登陆延迟
    //         try {
    //             Thread.sleep(1000);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //         if ("".equals(mPhone) || "".equals(mPassword)) {  //SharedPreferences 没有记录
    //             // TODO: 2017/5/2
    //             return false;
    //         }
    //         //// TODO: 2017/4/24 若服务器没开 则会等待很久
    //
    //         HashMap<String, String> hashMap = new HashMap<>();
    //         hashMap.put(Constant.USER_PHONE, mPhone);
    //         hashMap.put(Constant.USER_PASSWORD, mPassword);
    //         //登陆返回结果
    //         HttpResponse result = new HttpLogin().send(hashMap);
    //
    //         if (result == null) {
    //             return true;                            //网络不通时，使之进入app能查看历史信息
    //         } else if (result.getStatus() == 200) {  //登陆成功
    //             saveUserInfo((DataTokens) result.getData());        //保存用户信息 与token
    //             return true;
    //         } else {
    //             return false;
    //         }
    //     }
    //
    //     @Override
    //     protected void onPostExecute(Boolean aBoolean) {
    //         Log.d(TAG, "onPostExecute: " + aBoolean);
    //         if (aBoolean) {                 //尝试登陆成功  进入主界面
    //             MainActivity.actionStart(LoginActivity.this);
    //             overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    //             finish();
    //         } else {
    //             showLoginForm();            //尝试登陆失败  显示表单
    //         }
    //     }
    // }

    public void onClickLogin(View view) {
        mPhone = phone_edit.getText().toString();
        mPassword = password_edit.getText().toString();

        //// TODO: 2017/4/23 delete 通过测试密码进入主界面
        if (mPassword.equals(testPass)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Constant.SharedPreferences.ID, "111");
            editor.apply();
            MainActivity.actionStart(LoginActivity.this);
            return;
        }

        if (!checkFormat(mPhone, mPassword)) {    //若格式不对
            return;
        }

        new LoginBtnTask().execute();
    }

    /**
     * 通过按钮登陆   需告知用户登陆成功与否
     */
    private class LoginBtnTask extends AsyncTask<Void, Void, HttpResponse> {

        @Override
        protected HttpResponse doInBackground(Void... params) {
            //// TODO: 2017/4/24 若服务器没开 则会等待很久
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(Constant.HttpParam.Users.PHONE, mPhone);
            hashMap.put(Constant.HttpParam.Users.PASSWORD, mPassword);

            HttpResponse loginResponse = new HttpLogin().send(hashMap);

            if(loginResponse.getStatus()==200){
                saveUserInfo((DataTokens) loginResponse.getData());
            }

            return loginResponse;
        }

        @Override
        protected void onPostExecute(HttpResponse response) {
            if (response == null) {
                Toast.makeText(LoginActivity.this, "网络不通", Toast.LENGTH_SHORT).show();
                return;
            }
            if (response.getStatus() == 200) {
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                //跳转到主界面
                MainActivity.actionStart(LoginActivity.this);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "登陆失败，手机号或密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 检查手机号，密码格式
     */
    private boolean checkFormat(String phone, String password) {
        if ("".equals(phone) || "".equals(password)) {
            Toast.makeText(this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String r_phone = "\\d{11}";
        String r_password = "\\w{1,15}";
        if (phone.matches(r_phone) && password.matches(r_password)) {   //若格式不对
            return true;
        } else {
            Toast.makeText(this, "手机号和密码格式不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void onClickSignUp(View view) {
        SignUpActivity1.actionStart(this);
    }

    /**
     * 保存 登陆信息 用户信息 到  SharedPreferences 与 数据库
     *
     * 不能在ui线程中运行
     */
    private void saveUserInfo(DataTokens result) {
        String token = result.getToken();
        LogUtil.d(TAG, "saveUserInfo: token:" + token + "  uid:" + result.getUid() + "  phone:" + mPhone + " password:" + result.getToken());

        HttpUtil.setToken(token);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.SharedPreferences.ID, result.getUid());
        editor.putString(Constant.SharedPreferences.PHONE, mPhone);
        editor.putString(Constant.SharedPreferences.PASSWORD, mPassword);
        editor.apply();

        //保存 用户信息 到数据库
        HttpResponse response2 = new HttpGetUsersInfo().send(null);
        User user = (User) response2.getData();
        user.save();
    }

    private void init() {
        //初始化数据库
        Connector.getDatabase();
        login_form_layout = (LinearLayout) findViewById(R.id.login_form_layout);
        app_imageV = (ImageView) findViewById(R.id.app_imageV);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        sign_up_textV = (TextView) findViewById(R.id.sign_up_btn);
        preferences = getSharedPreferences(Constant.SharedPreferences.USERINFO, MODE_PRIVATE);
    }

    /**
     * 淡入登陆表单
     */
    private void showLoginForm() {
        translationY(app_imageV, 0, -100);
        translationY(login_form_layout, 50, -100);

        fadeIn(sign_up_textV);
        fadeIn(login_form_layout);
    }

    /**
     * 控件沿 y平移
     */
    private void translationY(View view, int from, int to) {
        from = DensityUtils.dp2px(view.getContext(), from);
        to = DensityUtils.dp2px(view.getContext(), to);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", from, to);
        animator.setDuration(1000);
        animator.start();
    }

    /**
     * 控件淡入
     */
    private void fadeIn(View view) {
        view.setAlpha(0);
        view.setVisibility(View.VISIBLE);
        ObjectAnimator showAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 0, 1);
        showAnimator.setDuration(1000);
        showAnimator.setTarget(view);
        showAnimator.start();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
