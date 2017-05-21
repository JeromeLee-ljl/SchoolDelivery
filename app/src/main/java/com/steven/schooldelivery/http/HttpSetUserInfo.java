package com.steven.schooldelivery.http;

import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/20.
 */

public class HttpSetUserInfo extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doPutWithToken(HttpUrl.USER_INFO,params);
    }

    @Override
    Object parse(String content) {
        return gson.fromJson(content, User.class);
    }
}
