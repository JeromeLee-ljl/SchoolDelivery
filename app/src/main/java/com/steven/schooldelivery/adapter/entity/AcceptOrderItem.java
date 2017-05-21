package com.steven.schooldelivery.adapter.entity;

/**
 * Created by 22340 on 2017/5/7.
 */

public class AcceptOrderItem {

    private String orderId;

    private String createTime;

    //快递名称
    private String expressName;
    private String price;

    private String pickupTime;
    private String pickupAddress;
    private String deliveryTime;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getOrderId() {

        return orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getExpressName() {
        return expressName;
    }

    public String getPrice() {
        return price;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }


}
