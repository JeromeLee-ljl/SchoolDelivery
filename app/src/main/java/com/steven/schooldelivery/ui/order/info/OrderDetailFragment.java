package com.steven.schooldelivery.ui.order.info;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.http.HttpGetDetailedOrder;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.ui.complain.ComplainActivity;
import com.steven.schooldelivery.util.LogUtil;
import com.steven.schooldelivery.util.Util;

import java.util.HashMap;

/**
 * Created by 22340 on 2017/5/10.
 */
@SuppressLint("ValidFragment")
public class OrderDetailFragment extends Fragment {
    private static final String TAG = "OrderDetailFragment";

    //ui
    private View mRootView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView pickup_time_textView, pickup_address_textView, express_code_textView, replacement_name_textView, replacement_phone_textView;
    private TextView delivery_time_textView, delivery_address_textView, recipient_name_textView, recipient_phone_textView;
    private TextView remark_textView;
    private TextView create_time_textView, order_id_textView, order_size_textView, order_cost_textView;
    private Button mComplainButton;

    // private String mOrderId;
    private DetailedOrder mOrder;


    public OrderDetailFragment(DetailedOrder order) {
        mOrder = order;
        LogUtil.d(TAG, "OrderDetailFragment: order:" + order);
        LogUtil.d(TAG, "OrderDetailFragment: orderID:" + order.getOrderId());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_order_info, container, false);

        init();

        return mRootView;
    }

    private void init() {
        initView();
        refresh();
    }


    /**
     * 将order的信息呈现在界面上，若不为空的话
     */
    private void showOrder(DetailedOrder order) {
        //取件信息
        pickup_time_textView.setText(Util.formatDate(order.getPickupTime()));
        pickup_address_textView.setText(order.getPickupAddress());
        express_code_textView.setText(order.getExpressCode());
        if(order.getReplacement()!=null){
            replacement_name_textView.setText(order.getReplacement().getName());
            replacement_phone_textView.setText(order.getReplacement().getPhone());
        }
        //送件信息
        delivery_time_textView.setText(Util.formatDate(order.getDeliveryTime()));
        delivery_address_textView.setText(order.getDeliveryAddress());
        recipient_name_textView.setText(order.getRecipient().getName());
        recipient_phone_textView.setText(order.getRecipient().getPhone());
        //备注
        remark_textView.setText(order.getRemark());
        //订单详情
        create_time_textView.setText(Util.formatDate(order.getCreateTime()));
        order_id_textView.setText(order.getOrderId());
        if (order.getPrice() == 5) {
            order_size_textView.setText("小件");
        } else if (order.getPrice() == 10) {
            order_size_textView.setText("大件");
        }
        order_cost_textView.setText(order.getPrice() + "元");

    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this::refresh);

        mComplainButton = (Button) mRootView.findViewById(R.id.complaint_button);
        mComplainButton.setOnClickListener(v -> complain());

        //取件信息
        pickup_time_textView = (TextView) mRootView.findViewById(R.id.pickup_time_textView);
        pickup_address_textView = (TextView) mRootView.findViewById(R.id.pickup_address_textView);
        express_code_textView = (TextView) mRootView.findViewById(R.id.express_code_textView);
        replacement_name_textView = (TextView) mRootView.findViewById(R.id.replacement_name_textView);
        replacement_phone_textView = (TextView) mRootView.findViewById(R.id.replacement_phone_textView);
        //送件信息
        delivery_time_textView = (TextView) mRootView.findViewById(R.id.delivery_time_textView);
        delivery_address_textView = (TextView) mRootView.findViewById(R.id.delivery_address_textView);
        recipient_name_textView = (TextView) mRootView.findViewById(R.id.recipient_name_textView);
        recipient_phone_textView = (TextView) mRootView.findViewById(R.id.recipient_phone_textView);
        //备注
        remark_textView = (TextView) mRootView.findViewById(R.id.remark_textView);
        //订单详情
        create_time_textView = (TextView) mRootView.findViewById(R.id.time_textView);
        order_id_textView = (TextView) mRootView.findViewById(R.id.order_id_textView);
        order_size_textView = (TextView) mRootView.findViewById(R.id.order_size_textView);
        order_cost_textView = (TextView) mRootView.findViewById(R.id.order_cost_textView);
    }

    /**
     * 根据订单状态判断  打开申述界面
     */
    private void complain() {
        switch (mOrder.getState()) {
            case WAIT_ACCEPT:
            case ACCEPTED:
            case TAKE_PARCEL_WAIT_DELIVERY:
                ComplainActivity.actionStart(getContext(),mOrder.getOrderId());
                break;
            default:
                Toast.makeText(getContext(),"不满足申诉条件",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 通过订单id 获取详细信息 并显示
     */
    private void refresh() {
        new Thread(() -> {
            HashMap<String, String> params = new HashMap<>();
            params.put("order_id", mOrder.getOrderId());
            HttpResponse response = new HttpGetDetailedOrder().send(params);

            getActivity().runOnUiThread(() -> {
                //// TODO: 2017/5/17 response是否要判空
                if (response.getStatus() == 200) {
                    showOrder((DetailedOrder) response.getData());
                } else {
                    Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            });
        }).start();
    }
}
