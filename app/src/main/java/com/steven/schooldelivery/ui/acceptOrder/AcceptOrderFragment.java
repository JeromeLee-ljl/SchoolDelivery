package com.steven.schooldelivery.ui.acceptOrder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.adapter.AcceptOrderItemAdapter;
import com.steven.schooldelivery.adapter.entity.AcceptOrderItem;
import com.steven.schooldelivery.db.Order;
import com.steven.schooldelivery.entity.OrderState;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by 22340 on 2017/5/7.
 */

public class AcceptOrderFragment extends Fragment{
    private static final String TAG = "AcceptOrderFragment";
    private View mRootView;
    private List<Order> mOrderList;
    private List<AcceptOrderItem> mAcceptOrderItemList;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView =  inflater.inflate(R.layout.fragment_order_accept, container, false);
            initData();
            getAcceptOrderItems();
            initView();
            refresh();
        }
        return mRootView;
    }

    /**
     * 刷新订单列表
     */
    private void refresh(){
        //// TODO: 2017/5/7 下拉刷新
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getActivity().runOnUiThread(() -> mSwipeRefreshLayout.setRefreshing(false));
        }).start();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent));
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AcceptOrderItemAdapter acceptOrderItemAdapter = new AcceptOrderItemAdapter(mAcceptOrderItemList);
        mRecyclerView.setAdapter(acceptOrderItemAdapter);
    }

    private void getAcceptOrderItems() {
        DateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA);
        for (Order order : mOrderList) {
            AcceptOrderItem acceptOrderItem = new AcceptOrderItem();

            acceptOrderItem.setOrderId(order.getOrderId());
            acceptOrderItem.setCreateTime(dateFormat.format(order.getCreatetime()));
            acceptOrderItem.setExpressName(order.getExpressName());
            acceptOrderItem.setDeliveryTime(dateFormat.format(order.getDeliveryTime()));
            acceptOrderItem.setPickupAddress(order.getPickupAddress());
            acceptOrderItem.setPickupTime(dateFormat.format(order.getPickupTime()));
            double price = order.getPrice();
            if(price==5){
                acceptOrderItem.setPrice("小件   "+String.valueOf(price)+"元");
            }else if (price==10){
                acceptOrderItem.setPrice("大件   "+String.valueOf(price)+"元");
            }else {
                acceptOrderItem.setPrice(String.valueOf(price)+"元");
            }
            mAcceptOrderItemList.add(acceptOrderItem);
        }
    }

    /**
     * 获取订单信息
     */
    // TODO: 2017/5/6 获取订单信息
    private void initData() {
        mOrderList =new ArrayList<>();
        mAcceptOrderItemList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            Order order1 = new Order();
            order1.setPrice(5.0);
            order1.setRecipientId("111");
            order1.setReplacementId("222");
            order1.setExpressName("圆通");
            order1.setState(OrderState.ACCEPTED);
            order1.setCreatetime(String.valueOf(new Timestamp(2017,5,1,10,55,10,0).getTime()));

            order1.setOrderId("123");
            order1.setFinishtime(String.valueOf(new Timestamp(2017,5,2,10,55,10,0).getTime()));
            order1.setPickupTime(String.valueOf(new Timestamp(2017,5,1,11,55,10,0).getTime()));
            order1.setDeliveryTime(String.valueOf(new Timestamp(2017,5,1,11,55,10,0).getTime()));
            order1.setGrade("good");
            order1.setRemark("备注：quickly");
            order1.setExpressCode("单号");
            order1.setPickupAddress("取件地点11111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
            order1.setPickupCode("取件号码");
            order1.setDeliveryAddress("送件地点");
            mOrderList.add(order1);


            Order order2 = new Order();
            order2.setPrice(10.0);
            order2.setRecipientId("111");
            order2.setReplacementId("222");
            order2.setExpressName("韵达");
            order2.setState(OrderState.ACCEPTED);
            order2.setCreatetime(String.valueOf(new Timestamp(2017,5,1,10,55,10,0).getTime()));

            order2.setOrderId("124");
            order2.setFinishtime(String.valueOf(new Timestamp(2017,5,2,10,55,10,0).getTime()));
            order2.setPickupTime(String.valueOf(new Timestamp(2017,5,1,11,55,10,0).getTime()));
            order2.setDeliveryTime(String.valueOf(new Timestamp(2017,5,1,11,55,10,0).getTime()));
            order2.setGrade("good");
            order2.setRemark("备注：quickly");
            order2.setExpressCode("单号");
            order2.setPickupAddress("取件地点");
            order2.setPickupCode("取件号码");
            order2.setDeliveryAddress("送件地点");
            mOrderList.add(order2);
        }
    }
}
