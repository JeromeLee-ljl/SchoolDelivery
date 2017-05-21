package com.steven.schooldelivery.adapter;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.adapter.entity.AcceptOrderItem;

import java.util.List;

/**
 * Created by 22340 on 2017/5/7.
 */

public class AcceptOrderItemAdapter extends RecyclerView.Adapter<AcceptOrderItemAdapter.ViewHolder> {

    private static final String TAG = "AcceptOrderItemAdapter";
    private List<AcceptOrderItem> mAcceptOrderItemList;
    public AcceptOrderItemAdapter(List<AcceptOrderItem> acceptOrderItems){
        mAcceptOrderItemList =acceptOrderItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_order_accept_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        //// TODO: 2017/5/7 onclick
        holder.accept_order_button.setOnClickListener(v -> {
            new AlertDialog.Builder(parent.getContext()).setTitle("是否接单").setPositiveButton("确定", (dialog, which) -> {
                // TODO: 2017/5/7
                Toast.makeText(parent.getContext(),"接单成功",Toast.LENGTH_SHORT).show();
            }).setNegativeButton("取消", (dialog, which) -> {
                // TODO: 2017/5/7
            }).show();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AcceptOrderItem acceptOrderItem = mAcceptOrderItemList.get(position);

        holder.create_time_textView.setText(acceptOrderItem.getCreateTime());
        holder.express_name_textView.setText(acceptOrderItem.getExpressName());
        holder.pickup_address_textView.setText(acceptOrderItem.getPickupAddress());
        holder.pickup_time_TextView.setText(acceptOrderItem.getDeliveryTime());
        holder.delivery_time_textView.setText(acceptOrderItem.getDeliveryTime());
        holder.price_textView.setText(acceptOrderItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return mAcceptOrderItemList.size();
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
