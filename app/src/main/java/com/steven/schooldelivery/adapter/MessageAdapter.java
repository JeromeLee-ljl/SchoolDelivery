package com.steven.schooldelivery.adapter;


import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.entity.MessageEntity;
import com.steven.schooldelivery.entity.MessageStateEnum;
import com.steven.schooldelivery.http.HttpSetMessageState;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.util.LogUtil;
import com.steven.schooldelivery.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/24.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private static final String TAG = "MessageAdapter";

    private List<MessageEntity> messages;

    public MessageAdapter(List<MessageEntity> messages) {
        this.messages = messages;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_message_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //点击读取详情
        viewHolder.constraintLayout.setOnClickListener(v -> {
            LogUtil.d(TAG, "onCreateViewHolder: readMessageInfo");
            int postion = viewHolder.getAdapterPosition();
            MessageEntity message = messages.get(postion);
            // TODO: 2017/5/24
            setMessageState(String.valueOf(message.getId()),MessageStateEnum.READ);//修改状态为已读

            View dialogLayout = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_message,null);
            AlertDialog dialog  = new AlertDialog.Builder(v.getContext()).create();
            dialog.setView(dialogLayout);
            TextView title = (TextView) dialogLayout.findViewById(R.id.titleTextView);
            title.setText(message.getTitle());
            TextView senderId = (TextView) dialogLayout.findViewById(R.id.senderTextView);
            senderId.setText(message.getSenderId());
            TextView content = (TextView) dialogLayout.findViewById(R.id.contentTextView);
            content.setText(message.getContent());
            Button button = (Button) dialogLayout.findViewById(R.id.notShowButton);
            button.setOnClickListener(v1 -> {
                LogUtil.d(TAG, "onCreateViewHolder: 修改消息状态为不显示");
                setMessageState(String.valueOf(message.getId()),MessageStateEnum.NUT_SHOW);//修改状态为不显示
                dialog.dismiss();
            });
            dialog.show();
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MessageEntity message = messages.get(i);
        viewHolder.messageTitle.setText(message.getTitle());
        viewHolder.createTime.setText(Util.formatDate(message.getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private void setMessageState(String messageId,MessageStateEnum state) {
        new Thread(() -> {
            Map<String, String> params = new HashMap<>();
            params.put("messageId",messageId);
            switch (state) {
                case READ:
                    params.put("state", "1");
                    break;
                case NUT_SHOW:
                    params.put("state", "2");
                    break;
                default:
                    params.put("state", "1");
                    break;
            }
            HttpResponse response = new HttpSetMessageState().send(params);
        }).start();

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private String id;
        private ConstraintLayout constraintLayout;
        private TextView messageTitle;
        private TextView createTime;

        public ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constraintLayout);
            messageTitle = (TextView) itemView.findViewById(R.id.messageTitleTextView);
            createTime = (TextView) itemView.findViewById(R.id.createTimeTextView);
        }
    }

}
