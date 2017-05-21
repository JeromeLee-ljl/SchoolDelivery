package com.steven.schooldelivery.ui.userinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.steven.schooldelivery.Constant;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.http.HttpSetUserInfo;
import com.steven.schooldelivery.http.HttpUrl;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class UpgradeActivity extends BaseActivity {
    private static final String TAG = "UpgradeActivity";
    private EditText idcard_editText,alipay_editText;

    private String mIdCard,mAlipay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        init();
    }

    private void init() {
        setToolBar("用户升级");
        initView();
    }

    private void initView() {
        //todo 提交照片
        idcard_editText = (EditText) findViewById(R.id.idcard_editText);
        alipay_editText = (EditText) findViewById(R.id.alipay_editText);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UpgradeActivity.class);
        context.startActivity(intent);
    }

    public void onClickUpgrade(View view) {
        mIdCard = idcard_editText.getText().toString();
        mAlipay = alipay_editText.getText().toString();

        if(checkFormat()){
            upGrade();
        }
    }

    private void upGrade() {
        new Thread(() -> {
            User user = User.getCurrentUser(this);

            Map<String,String> params = new HashMap<>();
            params.put(Constant.HttpParam.Users.IDCARD,mIdCard);
            params.put(Constant.HttpParam.Users.ALIPAY,mAlipay);
            //todo
            params.put(Constant.HttpParam.Users.PHOTO,"test");
            // params.put(Constant.HttpParam.Users.PHOTO,"");
            params.put(Constant.HttpParam.Users.SCHOLLCARD,user.getSchoolCard());

            HttpResponse response =new HttpSetUserInfo().send(params);
            if(response.getStatus()==200){
                User user1 = (User) response.getData();
                User.setCurrentUser(user1);

                Map<String,String> upgradeParams = new HashMap<>();
                upgradeParams.put("type","0");
                HttpResponse upgradeResponse = new Gson().fromJson(HttpUtil.doPostWithToken(HttpUrl.UPGRADE,upgradeParams),HttpResponse.class);
                runOnUiThread(() -> {
                    if(upgradeResponse.getStatus()==200){
                        Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"提交失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                runOnUiThread(() -> Toast.makeText(this,"提交失败",Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private boolean checkFormat() {
        if("".equals(mIdCard)||"".equals(mAlipay)){
            Toast.makeText(this,"请完善信息",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
