package com.steven.schooldelivery.ui.order.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.adapter.StateAdapter;
import com.steven.schooldelivery.http.HttpGetOrderStates;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.http.gson.OrderStateJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/8.
 */

public class OrderStateFragment extends Fragment {
    private static final String TAG = "OrderStateFragment";
    private View mRootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private String mOrderId;
    private List<OrderStateJson> mOrderStates;
    private StateAdapter mStateAdapter;

    public OrderStateFragment(String orderId) {
        mOrderId = orderId;
        mOrderStates = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_order_state, container, false);
        init();
        return mRootView;
    }

    private void init() {
        initView();
        refresh();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStateAdapter = new StateAdapter(mOrderStates);
        mRecyclerView.setAdapter(mStateAdapter);
    }


    /**
     * todo 刷新动作   通过orderId 查询订单所有状态
     */
    private void refresh() {
        new Thread(() -> {
            getOrderState();
            getActivity().runOnUiThread(() ->{
                Log.d(TAG, "refresh: size:"+mOrderStates.size());
                mStateAdapter.notifyDataSetChanged();
                // mStateAdapter.notifyAll();
                mSwipeRefreshLayout.setRefreshing(false);
            });
        }).start();
    }


    /**
     * todo 获取详细订单状态
     */
    private void getOrderState() {
        Map<String,String> params = new HashMap<>();
        params.put("order_id",mOrderId);
        HttpResponse response = new HttpGetOrderStates().send(params);
        List<OrderStateJson> orderStates = (List<OrderStateJson>) response.getData();
        mOrderStates.clear();
        mOrderStates.addAll(orderStates);
    }
}
