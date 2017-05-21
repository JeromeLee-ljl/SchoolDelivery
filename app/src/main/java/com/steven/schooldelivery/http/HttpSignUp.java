package com.steven.schooldelivery.http;

import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/3.
 */

public class HttpSignUp extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        return HttpUtil.doPost(HttpUrl.SIGN_UP,params);
    }

    @Override
    Object parse(String content) {
        return gson.fromJson(content, User.class);
    }
}
