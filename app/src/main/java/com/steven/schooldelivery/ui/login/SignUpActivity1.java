package com.steven.schooldelivery.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;

public class SignUpActivity1 extends BaseActivity {

    private EditText phone_edit, password_edit, sure_password_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        init();
    }

    private void init() {
        setToolBar("注册");
        initView();
    }

    private void initView() {
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        sure_password_editText = (EditText) findViewById(R.id.sure_password_editText);
    }

    public void onClickNext(View view) {
        String phone = phone_edit.getText().toString();
        String password = password_edit.getText().toString();
        String surePassword = sure_password_editText.getText().toString();

        if ("".equals(phone)) {
            // TODO: 2017/5/18 11位
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if ("".equals(password)) {
            Toast.makeText(this, "请填写密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(surePassword)) {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        SignUpActivity2.actionStart(this, phone, password);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SignUpActivity1.class);
        context.startActivity(intent);
    }
}
