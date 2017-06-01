package com.steven.schooldelivery.http;

import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/13.
 */

public class HttpChangeOrderState extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        String s = params.get("order_id");
        return HttpUtil.doPutWithToken(HttpUrl.SERVER_URL + "/orders/" + s+"/process", params);
    }

    @Override
    Object parse(String content) {
        return null;
    }

    // @Override
    // protected HttpResponse parseResult(String json) {
    //     HttpResponse response = JSON.parseObject(json, HttpResponse.class);
    //     JSONArray array = (JSONArray) response.getData();
    //     if (array == null) {              //content为空时，则不解析content
    //         return response;
    //     }
    //
    //     List<OrderStateJson> orderEntities = new ArrayList<>();
    //     for (Object e : array) {
    //         orderEntities.add(((JSONObject) e).toJavaObject(OrderStateJson.class));
    //     }
    //     response.setData(orderEntities);
    //     return response;
    // }
}
