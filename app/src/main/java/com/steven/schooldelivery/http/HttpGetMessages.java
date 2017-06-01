package com.steven.schooldelivery.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.steven.schooldelivery.entity.MessageEntity;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/24.
 */

public class HttpGetMessages extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doGetWithToken(HttpUrl.MESSAGES,new HashMap<>());
    }

    @Override
    Object parse(String content) {
        return null;
    }

    @Override
    protected HttpResponse parseResult(String json) {
        HttpResponse response = JSON.parseObject(json, HttpResponse.class);
        JSONArray array = (JSONArray) response.getData();
        if (array == null||array.size()==0) {              //content为空时，则不解析content
            return response;
        }

        List<MessageEntity> messageEntities = new ArrayList<>();
        for (Object e : array) {
            messageEntities.add(((JSONObject) e).toJavaObject(MessageEntity.class));
        }
        response.setData(messageEntities);
        return response;
    }
}
