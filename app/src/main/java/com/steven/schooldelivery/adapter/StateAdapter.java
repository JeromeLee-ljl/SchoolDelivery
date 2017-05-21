package com.steven.schooldelivery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.entity.OrdersOperationLogEntity;
import com.steven.schooldelivery.util.Util;

import java.util.List;

/**
 * Created by 22340 on 2017/5/13.
 */

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {
    private List<OrdersOperationLogEntity> orderStates;

    public StateAdapter(List<OrdersOperationLogEntity> ordersOperationLogEntities) {
        this.orderStates = ordersOperationLogEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_order_state_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrdersOperationLogEntity logEntity = orderStates.get(position);
        holder.title.setText(logEntity.getStateType());
        holder.time.setText(Util.formatDate(Util.DatePattern.MM_DD_HH_MM, logEntity.getStateChangeTime()));
        holder.info.setText("");// TODO: 2017/5/13
    }

    @Override
    public int getItemCount() {
        return orderStates.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;
        TextView info;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_textView);
            time = (TextView) itemView.findViewById(R.id.time_textView);
            info = (TextView) itemView.findViewById(R.id.info_textView);
        }
    }
}
