package com.steven.schooldelivery.http.gson;

/**
 * Created by finderlo on 2017/4/7.
 */
public class HttpResponse {
    /**
     * 错误码
     */
    private int status;
    /**
     * 描述
     */
    private String message;
    /**
     * 返回信息
     */
    private Object data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
