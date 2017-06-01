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
        String s = params.get("order_id");

        return HttpUtil.doGetWithToken(HttpUrl.SERVER_URL + "/orders/" + s, params);
    }

    @Override
    Object parse(String content) {
        DetailedOrder detailedOrder = gson.fromJson(content, DetailedOrder.class);
        // detailedOrder.setRecipient(gson.fromJson(detailedOrder.getRecipient().toString(), User.class));
        // detailedOrder.setReplacement(gson.fromJson(detailedOrder.getReplacement().toString(), User.class));
        return detailedOrder;
    }
}
