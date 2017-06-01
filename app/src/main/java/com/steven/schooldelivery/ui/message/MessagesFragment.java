package com.steven.schooldelivery.ui.message;

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

import com.alibaba.fastjson.JSON;
import com.steven.schooldelivery.Config;
import com.steven.schooldelivery.R;
import com.steven.schooldelivery.adapter.MessageAdapter;
import com.steven.schooldelivery.entity.MessageEntity;
import com.steven.schooldelivery.entity.MessageStateEnum;
import com.steven.schooldelivery.http.HttpGetMessages;
import com.steven.schooldelivery.http.gson.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {
    private static final String TAG = "MessagesFragment";

    private List<MessageEntity> messageEntities;

    private View mRootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private MessageAdapter mMessageAdapter;

    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.fragment_messages);
    //     init();
    // }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_messages, container, false);
            init();
        }
        return mRootView;
    }


    private void init() {
        messageEntities = new ArrayList<>();

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMessageAdapter = new MessageAdapter(messageEntities);
        mRecyclerView.setAdapter(mMessageAdapter);

        refresh();
    }

    private void refresh() {
        new Thread(() -> {
            getMessages();
            getActivity().runOnUiThread(() -> {
                mMessageAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            });
        }).start();
    }

    public void getMessages() {
        if (Config.ISDEBUG) {
            String testData1 = "{\n" +
                    "\t\t\t\t\t\t\"id\": 1,\n" +
                    "\t\t\t\t\t\t\"senderId\": \"213124127\",\n" +
                    "\t\t\t\t\t\t\"receiverId\": \"213124128\",\n" +
                    "\t\t\t\t\t\t\"type\": 1,\n" +
                    "\t\t\t\t\t\t\"createTime\": 1494928282000,\n" +
                    "\t\t\t\t\t\t\"title\": \"title\",\n" +
                    "\t\t\t\t\t\t\"content\": \"content\",\n" +
                    "\t\t\t\t\t\t\"state\": 0\n" +
                    "\t\t\t\t\t}";
            String testData2 = "{\n" +
                    "\t\t\t\t\t\t\"id\": 2,\n" +
                    "\t\t\t\t\t\t\"senderId\": \"213124127\",\n" +
                    "\t\t\t\t\t\t\"receiverId\": \"213124128\",\n" +
                    "\t\t\t\t\t\t\"type\": 1,\n" +
                    "\t\t\t\t\t\t\"createTime\": 1494928655000,\n" +
                    "\t\t\t\t\t\t\"title\": \"title\",\n" +
                    "\t\t\t\t\t\t\"content\": \"content\",\n" +
                    "\t\t\t\t\t\t\"state\": 0\n" +
                    "\t\t\t\t\t}";
            MessageEntity messageEntity1 = JSON.parseObject(testData1, MessageEntity.class);
            MessageEntity messageEntity2 = JSON.parseObject(testData2, MessageEntity.class);
            messageEntities.add(messageEntity1);
            messageEntities.add(messageEntity2);
        } else {
            HttpResponse response = new HttpGetMessages().send(null);
            if (response == null) {
                getActivity().runOnUiThread(() -> Toast.makeText(getContext(), R.string.no_conntected_net, Toast.LENGTH_SHORT).show());
            } else if (response.getStatus() == 200) {
                messageEntities.clear();

                for (MessageEntity message : (List<MessageEntity>) response.getData()) {
                    if (Integer.parseInt(message.getState()) != MessageStateEnum.NUT_SHOW.ordinal()) {
                        messageEntities.add(message);
                    }
                }

            }
        }

    }
}
