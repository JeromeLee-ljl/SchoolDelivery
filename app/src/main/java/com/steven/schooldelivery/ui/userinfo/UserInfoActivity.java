package com.steven.schooldelivery.ui.userinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.util.LogUtil;

public class UserInfoActivity extends BaseActivity {
    User mUser;

    String credit;

    //ui
    TextView user_name_textView,user_sex_textView, user_phone_textView, user_identity_textView;
    TextView user_schoolName_textView,user_schoolAddress_textView,user_schoolCard_textView;
    TextView user_idCard_textView,user_aliPay_textView,user_photo_textView;
    TextView creditTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        init();
    }

    private void init(){
        setToolBar("个人信息");
        initView();
        mUser = User.getCurrentUser(this);
        credit = getSharedPreferences("credit",MODE_PRIVATE).getString("credit","0");
        showUserInfo();
    }

    private void showUserInfo() {
        creditTextView.setText(credit);

        user_name_textView.setText(mUser.getName());
        user_sex_textView.setText(mUser.getSex());
        user_phone_textView.setText(mUser.getPhone());

        User.UserType type = mUser.getIdentity();
        user_identity_textView.setText(type.toString());

        user_schoolName_textView.setText(mUser.getSchoolName());
        user_schoolAddress_textView.setText(mUser.getSchoolAddress());
        user_schoolCard_textView.setText(mUser.getIdCard());

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.otherInfo_linearLayout);
        ConstraintLayout degrade_constraintLayout = (ConstraintLayout) findViewById(R.id.degrade_constraintLayout);
        ConstraintLayout upgrade_constraintLayout = (ConstraintLayout) findViewById(R.id.upgrade_constraintLayout);
        if (type== User.UserType.REPLACEMENT){//代取人
            linearLayout.setVisibility(View.VISIBLE);
            upgrade_constraintLayout.setVisibility(View.GONE);
            degrade_constraintLayout.setVisibility(View.VISIBLE);
            user_idCard_textView.setText(mUser.getIdCard());
            user_aliPay_textView.setText(mUser.getAliPay());
            user_photo_textView.setText(mUser.getPhoto());
        }else if (type== User.UserType.RECIPIENT){//收件人
            linearLayout.setVisibility(View.GONE);
            upgrade_constraintLayout.setVisibility(View.VISIBLE);
            degrade_constraintLayout.setVisibility(View.GONE);
        }
    }

    private void initView() {

        creditTextView = (TextView) findViewById(R.id.creditTextView);

        user_name_textView = (TextView) findViewById(R.id.user_name_textView);

        user_sex_textView = (TextView) findViewById(R.id.user_sex_textView);
        user_phone_textView = (TextView) findViewById(R.id.user_phone_textView);
        user_identity_textView = (TextView) findViewById(R.id.user_identity_textView);

        user_schoolName_textView = (TextView) findViewById(R.id.user_schoolName_textView);
        user_schoolAddress_textView = (TextView) findViewById(R.id.user_schoolAddress_textView);
        user_schoolCard_textView = (TextView) findViewById(R.id.user_schoolCard_textView);

        user_idCard_textView = (TextView) findViewById(R.id.user_idCard_textView);
        user_aliPay_textView = (TextView) findViewById(R.id.user_aliPay_textView);
        user_photo_textView = (TextView) findViewById(R.id.user_photo_textView);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    public void onClickUpgrade(View view) {
        LogUtil.d(TAG, "onClickUpgrade: ");
        UpgradeActivity.actionStart(this);
    }

    public void onClickDegrade(View view) {
        LogUtil.d(TAG, "onClickDegrade: ");
        Toast.makeText(this,"暂时不支持降级",Toast.LENGTH_SHORT).show();
    }
}
