package com.steven.schooldelivery.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/13.
 */

public class HttpGetOrders extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doGetWithToken(HttpUrl.GET_ALL_ORDER,params);
    }

    @Override
    Object parse(String content) {
        return null;
    }

    @Override
    protected HttpResponse parseResult(String json) {
        HttpResponse response = JSON.parseObject(json, HttpResponse.class);
        JSONArray array = (JSONArray) response.getData();
        if (array == null) {              //content为空时，则不解析content
            return response;
        }

        List<DetailedOrder> orderEntities = new ArrayList<>();
        for (Object e : array) {
            orderEntities.add(((JSONObject) e).toJavaObject(DetailedOrder.class));
        }
        response.setData(orderEntities);
        return response;
    }
}
