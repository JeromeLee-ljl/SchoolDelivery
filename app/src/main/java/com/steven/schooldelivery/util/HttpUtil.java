package com.steven.schooldelivery.util;

import com.steven.schooldelivery.Constant;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 22340 on 2017/4/30.
 */

public class HttpUtil {
    public static String doGet(String url){
        return getInstance().doget(url, null);
    }

    public static String doGet(String url, Map<String, String> params) {
        return getInstance().doget(url, params);
    }

    public static String doPost(String url, Map<String, String> params)   {
        return getInstance().dopost(url, params);
    }

    public static String doGetWithToken(String url, Map<String, String> params)   {
        return getInstance().dogetWithToken(url, params);
    }

    public static String doPutWithToken(String url, Map<String, String> params)   {
        return getInstance().doputWithToken(url, params);
    }

    public static String doPostWithToken(String url, Map<String, String> params)   {
        return getInstance().dopostWithToken(url, params);
    }

    public static void setToken(String token) {
        getInstance().setmToken(token);
    }


    private OkHttpClient okHttpClient = new OkHttpClient();
    private static final String TAG = "HttpUtil";
    private String mToken = "";

    private String dogetWithToken(String url, Map<String, String> params)   {
        params.put(Constant.TOKEN, getmToken());
        return doget(url, params);
    }

    private String doputWithToken(String url, Map<String, String> params)   {
        params.put(Constant.TOKEN, getmToken());
        return doput(url, params);
    }

    private String dopostWithToken(String url, Map<String, String> params)   {
        params.put(Constant.TOKEN, getmToken());
        return dopost(url, params);
    }

    private String doget(String url, Map<String, String> params){
        String response = null;
        try {
            response = dogetWithExp(url,params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    private String doput(String url, Map<String, String> params){
        String response = null;
        try {
            response = doputWithExp(url,params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String dopost(String url, Map<String, String> params){
        String response = null;
        try {
            response = dopostWithExp(url, params);
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return response;
    }

    private String dogetWithExp(String url, Map<String, String> params) throws IOException {
        LogUtil.d(TAG,url+" doget params:"+params);
        String newUrl = url + parseMap2Encode(params);

        Request request = new Request.Builder()
                .url(newUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();
        LogUtil.d(TAG,url+" doget response:"+responseString);

        if (response.isSuccessful()) {
            return responseString;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private String doputWithExp(String url, Map<String, String> params) throws IOException {
        LogUtil.d(TAG,url+" doput params:"+params);
        RequestBody requestBody = parseMap2RequestBody(params);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();
        LogUtil.d(TAG,url+" doput response: "+responseString);

        if (response.isSuccessful()) {
            return responseString;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private String dopostWithExp(String url, Map<String, String> params) throws IOException {
        LogUtil.d(TAG,url+" dopost params:"+params);
        RequestBody requestBody = parseMap2RequestBody(params);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();
        LogUtil.d(TAG,url+" dopost response: "+responseString);

        if (response.isSuccessful()) {
            return responseString;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 解析参数
     */
    private String parseMap2Encode(Map<String, String> params) {
        if (params == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
            builder.append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private RequestBody parseMap2RequestBody(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }


    private void setmToken(String mToken) {
        this.mToken = mToken;
    }

    private String getmToken() {
        return mToken;
    }

    private static class SingletonHolder {
        private static HttpUtil instance = new HttpUtil();
    }

    private static HttpUtil getInstance() {
        return SingletonHolder.instance;
    }
}
