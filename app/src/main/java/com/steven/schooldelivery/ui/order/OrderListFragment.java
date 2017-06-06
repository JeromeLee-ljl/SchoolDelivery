package com.steven.schooldelivery.ui.order;

import android.annotation.SuppressLint;
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
import com.steven.schooldelivery.entity.OrderStateEnum;
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
@SuppressLint("ValidFragment")
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
    public void refresh() {
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
                order1.setState(1);
                order1.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

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

                DetailedOrder order3 = new DetailedOrder();
                order3.setPrice(10.0);
                order3.setRecipientId("111");
                order3.setReplacementId("222");
                order3.setExpressName("圆通快递");
                order3.setState(2);
                order3.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order3.setOrderId("123");
                order3.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order3.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order3.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order3.setGrade("good");
                order3.setRemark("备注：quickly");
                order3.setExpressCode("单号");
                order3.setPickupAddress("取件地点");
                order3.setPickupCode("取件号码");
                order3.setDeliveryAddress("送件地点");

                mOrders.add(order3);
                
                
                DetailedOrder order4 = new DetailedOrder();
                order4.setPrice(10.0);
                order4.setRecipientId("111");
                order4.setReplacementId("222");
                order4.setExpressName("圆通快递");
                order4.setState(3);
                order4.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order4.setOrderId("123");
                order4.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order4.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order4.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order4.setGrade("good");
                order4.setRemark("备注：quickly");
                order4.setExpressCode("单号");
                order4.setPickupAddress("取件地点");
                order4.setPickupCode("取件号码");
                order4.setDeliveryAddress("送件地点");

                mOrders.add(order4);
                
                
                DetailedOrder order5 = new DetailedOrder();
                order5.setPrice(10.0);
                order5.setRecipientId("111");
                order5.setReplacementId("222");
                order5.setExpressName("圆通快递");
                order5.setState(4);
                order5.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order5.setOrderId("123");
                order5.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order5.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order5.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order5.setGrade("good");
                order5.setRemark("备注：quickly");
                order5.setExpressCode("单号");
                order5.setPickupAddress("取件地点");
                order5.setPickupCode("取件号码");
                order5.setDeliveryAddress("送件地点");

                mOrders.add(order5);
                
                
                DetailedOrder order6 = new DetailedOrder();
                order6.setPrice(10.0);
                order6.setRecipientId("111");
                order6.setReplacementId("222");
                order6.setExpressName("圆通快递");
                order6.setState(5);
                order6.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order6.setOrderId("123");
                order6.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order6.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order6.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order6.setGrade("good");
                order6.setRemark("备注：quickly");
                order6.setExpressCode("单号");
                order6.setPickupAddress("取件地点");
                order6.setPickupCode("取件号码");
                order6.setDeliveryAddress("送件地点");

                mOrders.add(order6);
                
                
                DetailedOrder order7 = new DetailedOrder();
                order7.setPrice(10.0);
                order7.setRecipientId("111");
                order7.setReplacementId("222");
                order7.setExpressName("圆通快递");
                order7.setState(6);
                order7.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order7.setOrderId("123");
                order7.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order7.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order7.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order7.setGrade("good");
                order7.setRemark("备注：quickly");
                order7.setExpressCode("单号");
                order7.setPickupAddress("取件地点");
                order7.setPickupCode("取件号码");
                order7.setDeliveryAddress("送件地点");

                mOrders.add(order7);
                
                
                DetailedOrder order8 = new DetailedOrder();
                order8.setPrice(10.0);
                order8.setRecipientId("111");
                order8.setReplacementId("222");
                order8.setExpressName("圆通快递");
                order8.setState(7);
                order8.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order8.setOrderId("123");
                order8.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order8.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order8.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order8.setGrade("good");
                order8.setRemark("备注：quickly");
                order8.setExpressCode("单号");
                order8.setPickupAddress("取件地点");
                order8.setPickupCode("取件号码");
                order8.setDeliveryAddress("送件地点");

                mOrders.add(order8);


                DetailedOrder order2 = new DetailedOrder();
                order2.setPrice(5.0);
                order2.setRecipientId("222");
                order2.setReplacementId("111");
                order2.setExpressName("圆通快递");
                order2.setState(1);
                order2.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

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
                
                
                DetailedOrder order9 = new DetailedOrder();
                order9.setPrice(5.0);
                order9.setRecipientId("222");
                order9.setReplacementId("111");
                order9.setExpressName("圆通快递");
                order9.setState(2);
                order9.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order9.setOrderId("124");
                order9.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order9.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order9.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order9.setGrade("good");
                order9.setRemark("备注：quickly");
                order9.setExpressCode("单号");
                order9.setPickupAddress("取件地点");
                order9.setPickupCode("取件号码");
                order9.setDeliveryAddress("送件地点");
                mOrders.add(order9);
                
                
                DetailedOrder order10 = new DetailedOrder();
                order10.setPrice(5.0);
                order10.setRecipientId("222");
                order10.setReplacementId("111");
                order10.setExpressName("圆通快递");
                order10.setState(3);
                order10.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order10.setOrderId("124");
                order10.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order10.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order10.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order10.setGrade("good");
                order10.setRemark("备注：quickly");
                order10.setExpressCode("单号");
                order10.setPickupAddress("取件地点");
                order10.setPickupCode("取件号码");
                order10.setDeliveryAddress("送件地点");
                mOrders.add(order10);
                
                DetailedOrder order11 = new DetailedOrder();
                order11.setPrice(5.0);
                order11.setRecipientId("222");
                order11.setReplacementId("111");
                order11.setExpressName("圆通快递");
                order11.setState(4);
                order11.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order11.setOrderId("124");
                order11.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order11.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order11.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order11.setGrade("good");
                order11.setRemark("备注：quickly");
                order11.setExpressCode("单号");
                order11.setPickupAddress("取件地点");
                order11.setPickupCode("取件号码");
                order11.setDeliveryAddress("送件地点");
                mOrders.add(order11);
                
                DetailedOrder order12 = new DetailedOrder();
                order12.setPrice(5.0);
                order12.setRecipientId("222");
                order12.setReplacementId("111");
                order12.setExpressName("圆通快递");
                order12.setState(5);
                order12.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order12.setOrderId("124");
                order12.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order12.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order12.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order12.setGrade("good");
                order12.setRemark("备注：quickly");
                order12.setExpressCode("单号");
                order12.setPickupAddress("取件地点");
                order12.setPickupCode("取件号码");
                order12.setDeliveryAddress("送件地点");
                mOrders.add(order12);
                
                
                DetailedOrder order13 = new DetailedOrder();
                order13.setPrice(5.0);
                order13.setRecipientId("222");
                order13.setReplacementId("111");
                order13.setExpressName("圆通快递");
                order13.setState(6);
                order13.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order13.setOrderId("124");
                order13.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order13.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order13.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order13.setGrade("good");
                order13.setRemark("备注：quickly");
                order13.setExpressCode("单号");
                order13.setPickupAddress("取件地点");
                order13.setPickupCode("取件号码");
                order13.setDeliveryAddress("送件地点");
                mOrders.add(order13);
                
                DetailedOrder order14 = new DetailedOrder();
                order14.setPrice(5.0);
                order14.setRecipientId("222");
                order14.setReplacementId("111");
                order14.setExpressName("圆通快递");
                order14.setState(7);
                order14.setCreateTime(String.valueOf(new Timestamp(2017, 5, 1, 10, 55, 10, 0).getTime()));

                order14.setOrderId("124");
                order14.setFinishtime(String.valueOf(new Timestamp(2017, 5, 2, 10, 55, 10, 0).getTime()));
                order14.setPickupTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order14.setDeliveryTime(String.valueOf(new Timestamp(2017, 5, 1, 11, 55, 10, 0).getTime()));
                order14.setGrade("good");
                order14.setRemark("备注：quickly");
                order14.setExpressCode("单号");
                order14.setPickupAddress("取件地点");
                order14.setPickupCode("取件号码");
                order14.setDeliveryAddress("送件地点");
                mOrders.add(order14);
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
                mOrders.addAll(DataSupport.order("createTime desc").find(DetailedOrder.class));
                break;
            case UNFINISHED:
                mOrders.clear();
                mOrders.addAll(DataSupport
                        .where("state != ?", String.valueOf(OrderStateEnum.COMPLETED.ordinal()))
                        .order("createTime desc")
                        .find(DetailedOrder.class));
                break;
            case FINISHED:
                mOrders.clear();
                mOrders.addAll(DataSupport
                        .where("state = ?", String.valueOf(OrderStateEnum.COMPLETED.ordinal()))
                        .order("createTime desc")
                        .find(DetailedOrder.class));
                break;
            default:
                mOrders.clear();
                mOrders.addAll(DataSupport.order("createTime desc").find(DetailedOrder.class));
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
        mOrderItemAdapter = new OrderItemAdapter(this, mOrders);
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
