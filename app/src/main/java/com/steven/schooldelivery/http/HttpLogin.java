package com.steven.schooldelivery.http;

import com.steven.schooldelivery.http.gson.DataTokens;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/3.
 */

public class HttpLogin extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doPost(HttpUrl.LOGIN,params);
    }

    @Override
    Object parse(String content) {
        return gson.fromJson(content, DataTokens.class);
    }
}
