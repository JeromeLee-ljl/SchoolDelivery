package com.steven.schooldelivery.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.steven.schooldelivery.Config;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.adapter.OrderItemAdapter;
import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.http.HttpGetOrders;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.util.LogUtil;

import org.litepal.crud.DataSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 22340 on 2017/5/4.
 */

public class OrderListFragment extends Fragment {
    private static final String TAG = "OrderListFragment";

    //ui
    private View mRootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<DetailedOrder> mOrders; //订单信息
    private OrderItemAdapter mOrderItemAdapter;

    //所有，未完成 等类型
    private TYPE mType = TYPE.ALL;

    public OrderListFragment(TYPE type) {
        mType = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_order_list, container, false);

            mOrders = new ArrayList<>();//在setAdapter之前初始化
            initView();
            refresh();
        }
        return mRootView;
    }

    /**
     * 刷新订单列表
     */
    private void refresh() {
        new Thread(() -> {
            // TODO: 2017/5/10 delete Thread.sleep(100);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getOrderData();
            getActivity().runOnUiThread(() -> {
                LogUtil.d(TAG, "refresh: size:"+mOrders.size());
                mOrderItemAdapter.notifyDataSetChanged();  //更新列表
                mSwipeRefreshLayout.setRefreshing(false);
            });
        }).start();
    }

    /**
     * 获取订单信息  另开线程
     */
    // TODO: 2017/5/6  获取订单详细信息  通过type进行筛选
    private void getOrderData() {
        if (Config.ISDEBUG) {
            for (int i = 0; i < 1; i++) {
                DetailedOrder order1 = new DetailedOrder();
                order1.setPrice(10.0);
                order1.setRecipientId("111");
                order1.setReplacementId("222");
                order1.setExpressName("圆通快递");
                order1.setState(2);
                order1.setCreatetime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order1.setOrderId("123");
                order1.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order1.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order1.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order1.setGrade("good");
                order1.setRemark("备注：quickly");
                order1.setExpressCode("单号");
                order1.setPickupAddress("取件地点");
                order1.setPickupCode("取件号码");
                order1.setDeliveryAddress("送件地点");

                mOrders.add(order1);


                DetailedOrder order2 = new DetailedOrder();
                order2.setPrice(5.0);
                order2.setRecipientId("222");
                order2.setReplacementId("111");
                order2.setExpressName("圆通快递");
                order2.setState(2);
                order2.setCreatetime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order2.setOrderId("124");
                order2.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order2.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order2.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order2.setGrade("good");
                order2.setRemark("备注：quickly");
                order2.setExpressCode("单号");
                order2.setPickupAddress("取件地点");
                order2.setPickupCode("取件号码");
                order2.setDeliveryAddress("送件地点");
                mOrders.add(order2);
            }
            return;
        }

        HttpResponse response = new HttpGetOrders().send(new HashMap<>());
        if (response == null) {
            getActivity().runOnUiThread(() -> Toast.makeText(getContext(), R.string.no_conntected_net, Toast.LENGTH_SHORT).show());
        } else if (response.getStatus() == 200) {
            List<DetailedOrder> orders = (List<DetailedOrder>) response.getData();
            if (orders != null) {
                // Log.d(TAG, "onCreateViewHolder: "+orders.get(0).getOrderId());
                DetailedOrder.saveAll(orders);
            }
        } else {

            getActivity().runOnUiThread(() -> Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_SHORT).show());
        }
        switch (mType) {
            case ALL:
                mOrders.clear();
                mOrders.addAll(DataSupport.findAll(DetailedOrder.class));
                break;
            case UNFINISHED:
                mOrders.clear();
                mOrders.addAll(DataSupport.findAll(DetailedOrder.class));
                break;
            case FINISHED:
                mOrders.clear();
                mOrders.addAll(DataSupport.findAll(DetailedOrder.class));
                break;
            default:
                mOrders.clear();
                mOrders.addAll(DataSupport.findAll(DetailedOrder.class));
                break;
        }
        LogUtil.d(TAG, "getOrderData: size:"+mOrders.size());

    }

    private void initView() {
        //下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.accent));
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mOrderItemAdapter = new OrderItemAdapter(getContext(), mOrders);
        mRecyclerView.setAdapter(mOrderItemAdapter);
    }

    /**
     * todo 添加其他种类  所有，未完成 等
     */
    public enum TYPE {
        ALL,
        UNFINISHED,
        FINISHED
    }
}
