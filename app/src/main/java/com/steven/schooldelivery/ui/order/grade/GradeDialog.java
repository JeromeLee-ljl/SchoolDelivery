package com.steven.schooldelivery.ui.order.grade;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.R;

/**
 * Created by 22340 on 2017/5/15.
 */

public class GradeDialog {
    private String mOrderId;
    private Dialog mDialog;
    private RatingBar ratingBar;
    private TextView gradeTextView;

    public GradeDialog(@NonNull Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_grader, null);
        gradeTextView = (TextView) view.findViewById(R.id.grade_textView);
        ratingBar = (RatingBar) view.findViewById(R.id.grade_ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            if(fromUser){
                gradeTextView.setText(""+rating*2);
            }
        });
        mDialog = new AlertDialog.Builder(context)
                .setTitle("评分")
                .setView(view)
                .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(context)
                                .setTitle("确认提交")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //// TODO: 2017/5/15 后台交互
                                        Toast.makeText(context, "test:" + mOrderId, Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("取消", (dialog1, which1) -> {
                                }).create()
                                .show();
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> {
                }).create();
    }

    public GradeDialog setOrderId(String orderId) {
        mOrderId = orderId;
        return this;
    }

    /**
     * 显示对话框
     */
    public void show() {
        ratingBar.setRating(4);
        gradeTextView.setText("" + ratingBar.getRating()*2);
        mDialog.show();
    }
}
