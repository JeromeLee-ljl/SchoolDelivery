package com.steven.schooldelivery.http.gson;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.steven.schooldelivery.db.DetailedOrder;

/**
 * Created by 22340 on 2017/5/22.
 */

public class OrderStateJson {
    @SerializedName("orderId")
    String orderId;
    @SerializedName("changeTime")
    String changeTime;

    @SerializedName("orderState")
    String orderState;
    @SerializedName("id")
    String id;
    @SerializedName("order")
    DetailedOrder order;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrder(DetailedOrder order) {
        this.order = order;
    }

    public String getOrderId() {

        return orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public String getId() {
        return id;
    }

    public DetailedOrder getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
