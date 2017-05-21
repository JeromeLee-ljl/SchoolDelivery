package com.steven.schooldelivery.http;

import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 22340 on 2017/5/18.
 */

public class HttpGetUsersInfo extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doGetWithToken(HttpUrl.USER_INFO,new HashMap<>());
    }

    @Override
    Object parse(String content) {
        return gson.fromJson(content, User.class);
    }
}
