package com.steven.schooldelivery.http;

import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/24.
 */

public class HttpSetMessageState extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        String messageId = params.get("messageId");
        return HttpUtil.doPutWithToken(HttpUrl.MESSAGES+"/"+messageId,params);
    }

    @Override
    Object parse(String content) {
        return null;
    }
}
