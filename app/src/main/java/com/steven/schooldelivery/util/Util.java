package com.steven.schooldelivery.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.steven.schooldelivery.db.DetailedOrder;
import com.steven.schooldelivery.http.gson.HttpResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by 22340 on 2017/5/13.
 */

public class Util {
    public  static String formatDate(DatePattern pattern,Date date){
        DateFormat dateFormat = new SimpleDateFormat(pattern.toString(), Locale.CHINA);
        return dateFormat.format(date);
    }
    public  static String formatDate(DatePattern pattern,String date){
        // Date date1 = new Date();
        return formatDate(pattern,new Date(Long.valueOf(date)));
    }
    public static String formatDate(String date){
        return formatDate(DatePattern.MM_DD_HH_MM,date);
    }
    public enum DatePattern{
        MM_DD_HH_MM("MM月dd日 HH:mm");

        String pattern;

        DatePattern(String s) {
            pattern =s;
        }

        @Override
        public String toString() {
            return pattern;
        }
    }


    public static HttpResponse parseJson(String json){
        HttpResponse response = JSON.parseObject(json, HttpResponse.class);
        JSONArray array = (JSONArray) response.getData();
        List<DetailedOrder> orderEntities = new ArrayList<>();

        for (Object e : array) {
            e = (JSONObject) e;
            orderEntities.add(((JSONObject) e).toJavaObject(DetailedOrder.class));
        }
        response.setData(orderEntities);
        return response;
    }



    public static <T extends Enum<T>> T enumFromOrigin(int origin, Class<T> classT) {
        T[] values = classT.getEnumConstants();
        for (T t : values) {
            Enum ty = (Enum) t;
            if (ty.ordinal() == origin) {
                return t;
            }
        }
        return null;
    }
    public static <T extends Enum<T>> T enumFromOrigin(String origin, Class<T> classT) {
        return enumFromOrigin(Integer.parseInt(origin),classT);
    }

}
