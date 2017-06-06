package com.steven.schooldelivery.ui.acceptOrder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.adapter.AcceptOrderItemAdapter;
import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.http.HttpGetAcceptOrders;
import com.steven.schooldelivery.http.gson.HttpResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 22340 on 2017/5/7.
 */

public class AcceptOrderFragment extends Fragment{
    private static final String TAG = "AcceptOrderFragment";
    private View mRootView;
    private List<DetailedOrder> mOrderList;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AcceptOrderItemAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView =  inflater.inflate(R.layout.fragment_order_accept, container, false);
            init();
        }
        return mRootView;
    }

    private void init() {
        mOrderList = new ArrayList<>();
        initView();
        refresh();
    }


    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent));
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AcceptOrderItemAdapter(this,mOrderList);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 刷新订单列表
     */
    public void refresh(){
        //// TODO: 2017/5/7 下拉刷新
        new Thread(() -> {
            getAcceptOrderItems();

            getActivity().runOnUiThread(() -> {
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            });
        }).start();
    }

    private void getAcceptOrderItems() {
        HttpResponse response = new HttpGetAcceptOrders().send(null);
        if(response==null) {
            getActivity().runOnUiThread(() -> Toast.makeText(getContext(), R.string.no_conntected_net, Toast.LENGTH_SHORT).show());
        }else if(response.getStatus()==200){
            List<DetailedOrder> detailedOrders = (List<DetailedOrder>) response.getData();
            mOrderList.clear();
            mOrderList.addAll(detailedOrders);
        }

        // for (Order order : mOrderList) {
        //     AcceptOrderItem acceptOrderItem = new AcceptOrderItem();
        //
        //     acceptOrderItem.setOrderId(order.getOrderId());
        //     acceptOrderItem.setCreateTime(Util.formatDate(order.getCreateTime()));
        //     acceptOrderItem.setExpressName(order.getExpressName());
        //     acceptOrderItem.setDeliveryTime(Util.formatDate(order.getDeliveryTime()));
        //     acceptOrderItem.setPickupAddress(order.getPickupAddress());
        //     acceptOrderItem.setPickupTime(Util.formatDate(order.getPickupTime()));
        //     double price = order.getPrice();
        //     if(price==5){
        //         acceptOrderItem.setPrice("小件   "+String.valueOf(price)+"元");
        //     }else if (price==10){
        //         acceptOrderItem.setPrice("大件   "+String.valueOf(price)+"元");
        //     }else {
        //         acceptOrderItem.setPrice(String.valueOf(price)+"元");
        //     }
        //     mAcceptOrderItemList.add(acceptOrderItem);
        // }
    }
}
