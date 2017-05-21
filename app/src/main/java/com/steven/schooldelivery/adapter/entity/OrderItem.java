package com.steven.schooldelivery.adapter.entity;

/**
 * Created by 22340 on 2017/5/6.
 */

public class OrderItem {
    private String orderId;

    private boolean isRecipient;//是否是收件人
    private String createTime;
    private String state;
    //快递名称
    private String expressName;
    private String price;

    //isRecipient = false
    private String recipientName;
    private String recipientPhone;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setIsRecipient(boolean recipient) {
        isRecipient = recipient;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getOrderId() {

        return orderId;
    }

    public boolean isRecipient() {
        return isRecipient;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getState() {
        return state;
    }

    public String getExpressName() {
        return expressName;
    }

    public String getPrice() {
        return price;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }
}
