package com.steven.schooldelivery.ui.userinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.util.LogUtil;

public class UserInfoActivity extends BaseActivity {
    User mUser;

    //ui
    TextView user_name_textView,user_sex_textView, user_phone_textView, user_identity_textView;
    TextView user_schoolName_textView,user_schoolAddress_textView,user_schoolCard_textView;
    TextView user_idCard_textView,user_aliPay_textView,user_photo_textView;

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
        Log.e(TAG, "init: "+(mUser==null));
        showUserInfo();
    }

    private void showUserInfo() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1){
            if (resultCode==RESULT_OK){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClickUpgrade(View view) {
        LogUtil.d(TAG, "onClickUpgrade: ");

        // final AlertDialog dialog = new AlertDialog.Builder(this).create();
        // // dialog.
        // View view1 = View.inflate(this,R.layout.activity_upgrade,null);
        //
        // Button button = (Button) view1.findViewById(R.id.upgrade_button);
        // button.setOnClickListener(v -> {
        //     // dialog.dismiss();
        //     AlertDialog dialog1 = new AlertDialog.Builder(this).create();
        //     View view2 = View.inflate(this,R.layout.fragment_myinfo,null);
        //     dialog1.setView(view2);
        //     dialog1.show();
        // });
        // dialog.setView(view1);
        // // dialog
        //
        // dialog.show();

        UpgradeActivity.actionStart(this);
    }

    public void onClickDegrade(View view) {

    }
}
