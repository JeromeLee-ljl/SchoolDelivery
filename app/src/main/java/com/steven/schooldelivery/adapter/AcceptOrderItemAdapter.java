package com.steven.schooldelivery.adapter;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.entity.OrderStateEnum;
import com.steven.schooldelivery.http.HttpChangeOrderState;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/7.
 */

public class AcceptOrderItemAdapter extends RecyclerView.Adapter<AcceptOrderItemAdapter.ViewHolder> {

    private static final String TAG = "AcceptOrderItemAdapter";
    private List<DetailedOrder> mDetailedOrders;
    private Activity mActivity;
    public AcceptOrderItemAdapter(Activity activity,List<DetailedOrder> detailedOrders){
        mActivity = activity;
        mDetailedOrders =detailedOrders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_order_accept_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        //// TODO: 2017/5/7 onclick
        holder.accept_order_button.setOnClickListener(v -> {
            new AlertDialog.Builder(parent.getContext()).setTitle("是否接单").setPositiveButton("确定", (dialog, which) -> {
                new Thread(() -> {
                    DetailedOrder order = mDetailedOrders.get(holder.getAdapterPosition());
                    Map<String,String> params = new HashMap<>();
                    params.put("order_id",order.getOrderId());
                    params.put("state", String.valueOf(OrderStateEnum.ACCEPTED.ordinal()));
                    HttpResponse response = new HttpChangeOrderState().send(params);
                    mActivity.runOnUiThread(() -> {
                        if(response.getStatus()==200){
                            Toast.makeText(v.getContext(),"接单成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(v.getContext(),"接单失败："+response.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }).start();
                // Toast.makeText(parent.getContext(),"接单成功",Toast.LENGTH_SHORT).show();
            }).setNegativeButton("取消", (dialog, which) -> {
                // TODO: 2017/5/7
            }).show();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DetailedOrder detailedOrder = mDetailedOrders.get(position);

        holder.create_time_textView.setText(Util.formatDate(detailedOrder.getCreatetime()));
        holder.express_name_textView.setText(detailedOrder.getExpressName());
        holder.pickup_address_textView.setText(detailedOrder.getPickupAddress());
        holder.pickup_time_TextView.setText(Util.formatDate(detailedOrder.getDeliveryTime()));
        holder.delivery_time_textView.setText(Util.formatDate(detailedOrder.getDeliveryTime()));
        double price = detailedOrder.getPrice();
        if(price==5){
            holder.price_textView.setText("小件");
        }else {
            holder.price_textView.setText("大件");
        }
    }

    @Override
    public int getItemCount() {
        return mDetailedOrders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView create_time_textView;
        TextView express_name_textView;
        TextView pickup_time_TextView;
        TextView pickup_address_textView;
        TextView delivery_time_textView;
        TextView price_textView;
        Button accept_order_button;
        public ViewHolder(View itemView) {
            super(itemView);
            create_time_textView = (TextView) itemView.findViewById(R.id.time_textView);
            express_name_textView = (TextView) itemView.findViewById(R.id.express_name_textView);
            pickup_address_textView = (TextView) itemView.findViewById(R.id.pickup_address_textView);
            pickup_time_TextView = (TextView) itemView.findViewById(R.id.pickup_time_TextView);
            delivery_time_textView = (TextView) itemView.findViewById(R.id.delivery_time_textView);
            price_textView = (TextView) itemView.findViewById(R.id.price_textView);
            accept_order_button = (Button) itemView.findViewById(R.id.accept_order_button);
        }
    }
}
