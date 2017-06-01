package com.steven.schooldelivery.ui.complain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.steven.schooldelivery.Constant;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.http.HttpComplain;
import com.steven.schooldelivery.http.gson.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class ComplainActivity extends BaseActivity {

    private String orderId;

    private RadioGroup mRadioGroup;
    private EditText info_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        init();
    }

    private void init() {
        orderId = getIntent().getStringExtra(Constant.HttpParam.Complaints.ORDERID);
        setToolBar("订单申述");
        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        info_editText = (EditText) findViewById(R.id.info_editText);
    }

    public void onClickComplain(View view) {
        String info = info_editText.getText().toString();
        if ("".equals(info)) {
            Toast.makeText(this, "请填写描述", Toast.LENGTH_SHORT).show();
            return;
        }
        int type;
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.type_radioButton:
                type = 0;
                break;
            case R.id.type_radioButton2:
                type = 1;
                break;
            case R.id.type_radioButton3:
                type = 2;
                break;
            case R.id.type_radioButton4:
                type = 3;
                break;
            case R.id.type_radioButton5:
                type = 4;
                break;
            default:
                type = 5;
                break;
        }
        int finalType = type;
        new Thread(() -> {
            Map<String,String> params = new HashMap<>();
            params.put(Constant.HttpParam.Complaints.TYPE,String.valueOf(finalType));
            params.put(Constant.HttpParam.Complaints.DESCRIPTION,info);
            params.put(Constant.HttpParam.Complaints.ORDERID,orderId);
            HttpResponse response = new HttpComplain().send(params);
            runOnUiThread(() -> {
                if (response.getStatus() == 200) {
                    // TODO: 2017/5/17 要不要对创建申述的结果进行处理 response是否要判空
                    Toast.makeText(this,"申述提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }


    public static void actionStart(Context context,String orderId){
        Intent intent = new Intent(context, ComplainActivity.class);
        intent.putExtra(Constant.HttpParam.Complaints.ORDERID,orderId);
        context.startActivity(intent);
    }
}
