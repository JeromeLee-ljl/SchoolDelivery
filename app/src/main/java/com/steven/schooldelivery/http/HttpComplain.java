package com.steven.schooldelivery.http;

import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/17.
 */

public class HttpComplain extends HttpSend{
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doPostWithToken(HttpUrl.ORDER_COMPLAIN,params);
    }

    @Override
    Object parse(String content) {
        return null;
    }
}
