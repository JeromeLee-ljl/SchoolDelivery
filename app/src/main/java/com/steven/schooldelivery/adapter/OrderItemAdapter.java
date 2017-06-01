package com.steven.schooldelivery.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.db.Order;
import com.steven.schooldelivery.ui.order.grade.GradeDialog;
import com.steven.schooldelivery.ui.order.info.OrderInfoActivity;
import com.steven.schooldelivery.util.LogUtil;
import com.steven.schooldelivery.util.Util;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 22340 on 2017/5/7.
 */

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private static final String TAG = "OrderItemAdapter";

    private List<DetailedOrder> mOrders;
    private String mUserId;

    private GradeDialog mGradeDialog;

    public OrderItemAdapter(Context context,List<DetailedOrder> orders){
        mOrders = orders;

        SharedPreferences sharedPreferences = context.getSharedPreferences("login_info", MODE_PRIVATE);
        mUserId = sharedPreferences.getString("userId","");
        mGradeDialog = new GradeDialog(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaper_order_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        //// TODO: 2017/5/7 点击进入详情页面
        holder.cardView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            OrderInfoActivity.actionStart(parent.getContext(),mOrders.get(position));
        });
        //// TODO: 2017/5/7 orderItem绑定事件  note: 区分收件与代取
        holder.cancel_order_btn.setOnClickListener(v -> {
            String orderId = getOrderId(holder);
            Toast.makeText(parent.getContext(),"取消：orderId: "+orderId,Toast.LENGTH_SHORT).show();
        });
        holder.pickup_btn.setOnClickListener(v -> {
            String orderId = getOrderId(holder);
            Toast.makeText(parent.getContext(),"确认领取：orderId: "+orderId,Toast.LENGTH_SHORT).show();
        });
        holder.confirm_delivery_btn.setOnClickListener(v -> {
            String orderId = getOrderId(holder);
            Toast.makeText(parent.getContext(),"确认送达：orderId: "+orderId,Toast.LENGTH_SHORT).show();
        });
        holder.comment_btn.setOnClickListener(v -> {
            String orderId = getOrderId(holder);
            mGradeDialog.setOrderId(orderId).show();//评价
        });
        return holder;
    }

    public String getOrderId(final ViewHolder holder){
        int position = holder.getAdapterPosition();
        return mOrders.get(position).getOrderId();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // LogUtil.d(TAG, "onBindViewHolder:position: "+position);
        Order order = mOrders.get(position);
        //设置是收件单还是 代取单
        if(mUserId.equals(order.getRecipientId())){            //此单为收件单
            holder.type_recipient_textView.setVisibility(View.VISIBLE);
            holder.type_replace_textView.setVisibility(View.INVISIBLE);

            holder.recipient_info_RelativeLayout.setVisibility(View.GONE);//收件不需要收件人的信息
            holder.recipient_name_textView.setText("");
            holder.recipient_phone_textView.setText("");
        }else if (mUserId.equals(order.getReplacementId())){    //此单为代取单
            // TODO: 2017/5/11
            holder.type_recipient_textView.setVisibility(View.INVISIBLE);
            holder.type_replace_textView.setVisibility(View.VISIBLE);

            holder.recipient_info_RelativeLayout.setVisibility(View.GONE);//不显示  //代取单需要收件人的信息
            // holder.recipient_name_textView.setText(order.getRecipientName());
            // holder.recipient_phone_textView.setText(order.getRecipientPhone());
        }

        holder.create_time_textView.setText(Util.formatDate(Util.DatePattern.MM_DD_HH_MM,order.getCreatetime()));
        holder.order_state_textView.setText(order.getState().toString());
        holder.express_name_textView.setText(order.getExpressName());
        holder.price_textView.setText("费用："+String.valueOf(order.getPrice())+"元");

        //todo 按钮显示
        // switch (orderItem.getState()){
        //     case "":
        //         break;
        //     default:
        //         break;
        // }
    }

    @Override
    public int getItemCount() {
        LogUtil.d(TAG, "getItemCount: "+mOrders.size());
        return mOrders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;   //点击事件

        TextView type_recipient_textView;  //收件人显示的图标
        TextView type_replace_textView;    //代取人显示的图标
        TextView create_time_textView;
        TextView order_state_textView;
        TextView express_name_textView;
        TextView price_textView;

        TextView recipient_name_textView;
        TextView recipient_phone_textView;
        RelativeLayout recipient_info_RelativeLayout;

        Button cancel_order_btn;
        Button pickup_btn;
        Button confirm_delivery_btn;
        Button comment_btn;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            type_recipient_textView = (TextView) itemView.findViewById(R.id.type_recipient_textView);
            type_replace_textView = (TextView) itemView.findViewById(R.id.type_replace_textView);

            create_time_textView = (TextView) itemView.findViewById(R.id.time_textView);
            order_state_textView = (TextView) itemView.findViewById(R.id.order_state_textView);
            express_name_textView = (TextView) itemView.findViewById(R.id.express_name_textView);
            price_textView  = (TextView) itemView.findViewById(R.id.price_textView);

            recipient_name_textView = (TextView) itemView.findViewById(R.id.recipient_name_textView);
            recipient_phone_textView = (TextView) itemView.findViewById(R.id.recipient_phone_textView);
            recipient_info_RelativeLayout = (RelativeLayout) itemView.findViewById(R.id.recipient_info_RelativeLayout);

            cancel_order_btn = (Button) itemView.findViewById(R.id.cancel_order_btn);
            pickup_btn = (Button) itemView.findViewById(R.id.pickup_btn);
            confirm_delivery_btn = (Button) itemView.findViewById(R.id.confirm_delivery_btn);
            comment_btn = (Button) itemView.findViewById(R.id.comment_btn);
        }
    }

}
