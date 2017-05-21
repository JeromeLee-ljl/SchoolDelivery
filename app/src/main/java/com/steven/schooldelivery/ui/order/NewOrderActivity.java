package com.steven.schooldelivery.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.steven.schooldelivery.Constant;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.http.HttpUrl;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.util.HttpUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class NewOrderActivity extends BaseActivity {
    private static final String TAG = "NewOrderActivity";

    EditText express_name_edit, express_code_edit, pickup_address_edit,
            pickup_time_edit, pickup_code_edit, delivery_address_edit,
            delivery_time_edit, orders_remark_edit;

    RadioGroup radioGroup;
    String express_name, express_code, pickup_address,
            pickup_time, pickup_code, delivery_address,
            delivery_time, orders_remark, delivery_size,order_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        initView();
    }

    private void initView() {
        //导航栏
        setToolBar("新建订单");

        //edittext
        express_name_edit = (EditText) findViewById(R.id.express_name_edit);
        express_code_edit = (EditText) findViewById(R.id.express_code_edit);

        pickup_address_edit = (EditText) findViewById(R.id.pickup_address_edit);
        pickup_time_edit = (EditText) findViewById(R.id.pickup_time_edit);
        pickup_code_edit = (EditText) findViewById(R.id.pickup_code_edit);

        delivery_address_edit = (EditText) findViewById(R.id.delivery_address_edit);
        delivery_time_edit = (EditText) findViewById(R.id.delivery_time_edit);
        orders_remark_edit = (EditText) findViewById(R.id.orders_remark_edit);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, NewOrderActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void onClickCreateOrder(View view) {
        getValue();
        if(!checkValue()){
            return;
        }

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Constant.HttpParam.Orders.EXPRESSNAME, express_name);
                hashMap.put(Constant.HttpParam.Orders.EXPRESSCODE, express_code);
                hashMap.put(Constant.HttpParam.Orders.PICKUPTIME, pickup_time);
                hashMap.put(Constant.HttpParam.Orders.PICKUPCODE, pickup_code);
                hashMap.put(Constant.HttpParam.Orders.PICKUPADDRESS, pickup_address);
                hashMap.put(Constant.HttpParam.Orders.DELIVERYADDRESS, delivery_address);
                hashMap.put(Constant.HttpParam.Orders.DELIVERYTIME, delivery_time);
                hashMap.put(Constant.HttpParam.Orders.ORDERREMARK, orders_remark);
                hashMap.put(Constant.HttpParam.Orders.PRICE,order_price);

                String response = HttpUtil.doPostWithToken(HttpUrl.ORDER_CREATE,hashMap);
                HttpResponse httpResponse=new Gson().fromJson(response,HttpResponse.class);

                return httpResponse.getStatus() == 200;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(NewOrderActivity.this,"订单创建成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(NewOrderActivity.this,"订单创建失败",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


    /**
     * 格式检查
     */
    private boolean checkValue() {
        // TODO: 2017/5/5
        return true;
    }

    public void getValue() {
        express_name = express_name_edit.getText().toString();
        express_code = express_code_edit.getText().toString();
        pickup_address = pickup_address_edit.getText().toString();
        pickup_code = pickup_code_edit.getText().toString();
        delivery_address = delivery_address_edit.getText().toString();
        orders_remark = orders_remark_edit.getText().toString();

        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        delivery_size = radioButton.getText().toString();
        if("大".equals(delivery_size)){
            order_price = "5";
        }else {
            order_price = "10";
        }
    }

    /**
     * 送件时间
     * 3天内
     */
    public void onClickSetSendTime(View view) {
        Calendar calendar = Calendar.getInstance();
        long mintime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_WEEK,6);
        long maxtime = calendar.getTimeInMillis();
        TimePickerDialog timePickerDialog = new TimePickerDialog.Builder()
                .setCallBack((timePickerView, millseconds) -> {
                    Timestamp timestamp = new Timestamp(millseconds);
                    delivery_time = timestamp.toString();                              //传递给后台的格式
                    Date date = new Date(millseconds);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);//显示格式
                    ((TextView)view).setText(dateFormat.format(date));
                })
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("送件时间")
                .setThemeColor(getResources().getColor(R.color.primary_dark))
                .setMinMillseconds(mintime)
                .setCurrentMillseconds(mintime)
                .setMaxMillseconds(maxtime)
                .setCyclic(false)
                .setWheelItemTextSize(18)
                .build();
        timePickerDialog.show(getSupportFragmentManager(),"timePicker");
    }

    /**
     * 取件时间
     * 选择30分钟到 3天
     * @param view
     */
    public void onClickSetPickUpTime(View view) {
        Calendar calendar = Calendar.getInstance();
        long mintime = calendar.getTimeInMillis()+30*60*1000;
        calendar.add(Calendar.DAY_OF_WEEK,3);
        long maxtime = calendar.getTimeInMillis();
        TimePickerDialog timePickerDialog = new TimePickerDialog.Builder()
                .setCallBack((timePickerView, millseconds) -> {
                    Timestamp timestamp = new Timestamp(millseconds);
                    pickup_time = timestamp.toString();                              //传递给后台的格式
                    Date date = new Date(millseconds);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.CHINA);//显示格式
                    ((TextView)view).setText(dateFormat.format(date));
                })
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("取件时间")
                .setThemeColor(getResources().getColor(R.color.primary_dark))
                .setMinMillseconds(mintime)
                .setCurrentMillseconds(mintime)
                .setMaxMillseconds(maxtime)
                .setCyclic(false)
                .setWheelItemTextSize(18)
                .build();
        timePickerDialog.show(getSupportFragmentManager(),"timePicker");
    }
}
