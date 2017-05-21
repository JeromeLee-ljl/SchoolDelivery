package com.steven.schooldelivery.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * gson使用toJson时处理数据库中的date类型的时间数据
 *
 * @author jinjinwang <br>
 *         created on Jun 9, 2013 11:48:29 AM
 */
public class SQLDateTypeAdapter implements JsonSerializer<Date> {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public JsonElement serialize(java.sql.Date src, Type arg1, JsonSerializationContext arg2) {
        String dateFormatAsString = format.format(new java.sql.Date(src.getTime()));
        return new JsonPrimitive(dateFormatAsString);
    }
}
