package com.steven.schooldelivery.http;

import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/13.
 */

public class HttpGetDetailedOrder extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doGetWithToken(null, params);
        // return HttpUtil.doGetWithToken(HttpUrl.ORDER_FIND_DETAILED_ORDER, params);
    }

    @Override
    Object parse(String content) {
        return gson.fromJson(content, DetailedOrder.class);
    }
}
