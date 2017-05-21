package com.steven.schooldelivery.http;

import com.google.gson.reflect.TypeToken;
import com.steven.schooldelivery.entity.OrdersOperationLogEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/13.
 */

public class HttpGetOrderStates extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return null;
        // return HttpUtil.doGetWithToken(HttpUrl.ORDER_FIND_ORDER_LOG,params);
    }

    @Override
    Object parse(String content) {
        return gson.fromJson(content,new TypeToken<List<OrdersOperationLogEntity>>(){}.getType());
    }
}
