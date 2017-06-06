package com.steven.schooldelivery.http;

import com.steven.schooldelivery.util.HttpUtil;

import java.util.Map;

/**
 * Created by 22340 on 2017/6/2.
 */

public class HttpGetCredit extends HttpSend {
    @Override
    String request(Map<String, String> params) {
        String userId = params.get("uid");
        return HttpUtil.doGetWithToken(HttpUrl.CREDIT+"/"+userId,params);
    }

    @Override
    Object parse(String content) {
        return gson.fromJson(content,CreditResponse.class);
    }

    public static class CreditResponse{
        private String uid;
        private String credit_value;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setCredit_value(String credit_value) {
            this.credit_value = credit_value;
        }

        public String getUid() {
            return uid;
        }

        public String getCredit_value() {
            return credit_value;
        }
    }
}
