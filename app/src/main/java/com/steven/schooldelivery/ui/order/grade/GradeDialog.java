package com.steven.schooldelivery.ui.order.grade;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.steven.schooldelivery.Constant;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.adapter.OrderItemAdapter;
import com.steven.schooldelivery.entity.OrderStateEnum;
import com.steven.schooldelivery.http.HttpChangeOrderState;
import com.steven.schooldelivery.http.gson.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/15.
 */

public class GradeDialog {
    private String mOrderId;
    private Dialog mDialog;
    private RatingBar ratingBar;

    public GradeDialog(@NonNull Activity activity, OrderItemAdapter orderItemAdapter) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_grader, null);
        // gradeTextView = (TextView) view.findViewById(R.id.grade_textView);
        ratingBar = (RatingBar) view.findViewById(R.id.grade_ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            if (fromUser) {
                mDialog.setTitle("评分    " + rating * 2);
                // gradeTextView.setText(""+rating*2);
            }
        });
        mDialog = new AlertDialog.Builder(activity)
                .setTitle("评分")
                .setView(view)
                .setPositiveButton("提交", (dialog, which) -> new AlertDialog.Builder(activity)
                        .setTitle("确认提交")
                        .setPositiveButton("确定", (dialog12, which12) -> {
                            ////后台交互
                            new Thread(() -> {
                                Map<String, String> params = new HashMap<>();
                                params.put(Constant.HttpParam.Orders.STATE, String.valueOf(OrderStateEnum.COMPLETED.ordinal()));
                                params.put("order_id", mOrderId);
                                params.put("grade", String.valueOf((int)ratingBar.getRating()));
                                HttpResponse response = new HttpChangeOrderState().send(params);

                                activity.runOnUiThread(() -> {
                                    if (response.getStatus() == 200) {
                                        Toast.makeText(activity, "成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(activity, "失败:\n" + response.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                    orderItemAdapter.notifyDataSetChanged();
                                });
                            }).start();
                        })
                        .setNegativeButton("取消", (dialog1, which1) -> {
                        }).create()
                        .show())
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
        mDialog.setTitle("评分    " + ratingBar.getRating() * 2);
        mDialog.show();
    }
}
