package com.steven.schooldelivery.http;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.steven.schooldelivery.http.gson.HttpResponse;

import java.util.Map;

/**
 * Created by 22340 on 2017/5/3.
 */

public abstract class HttpSend {
    static final String TAG = "HttpSend";
    Gson gson;

    public HttpSend(){
        // GsonBuilder builder = new GsonBuilder();
        // // builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // // builder.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter()).registerTypeAdapter(java.sql.Date.class, new SQLDateTypeAdapter());
        // // Register an adapter to manage the date types as long values
        // builder.setLongSerializationPolicy(LongSerializationPolicy.DEFAULT);
        // // builder.registerTypeAdapter(Timestamp.class, new JsonDeserializer<Timestamp>() {
        // //     public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // //         return new Timestamp(json.getAsJsonPrimitive().getAsLong());
        // //     }
        // // });
        // gson = builder.create();
        gson = new Gson();
    }

    public HttpResponse send(Map<String, String> params) {
        String response = request(params);
        HttpResponse result = null;
        if (response != null) {
            result = parseResult(response);
        }
        return result;
    }

    /**
     * 解析返回结果
     */
    protected HttpResponse parseResult(String response) {
        // HttpResponse result = gson.fromJson(response, HttpResponse.class);
        HttpResponse result = JSON.parseObject(response, HttpResponse.class);

        Object content = result.getData();
        if (content == null) {              //content为空时，则不解析content
            return result;
        }

        result.setData(parse(content.toString()));

        return result;
    }

    /**
     * 在子类中确定请求url
     */
    abstract String request(Map<String, String> params);

    /**
     * 解析返回结果中的content
     */
    abstract Object parse(String content);
}
