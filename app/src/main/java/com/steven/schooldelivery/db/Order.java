package com.steven.schooldelivery.db;

import com.google.gson.annotations.SerializedName;
import com.steven.schooldelivery.entity.OrderState;

import org.litepal.crud.DataSupport;

import java.io.Serializable;


public class Order extends DataSupport  implements Serializable {
    @SerializedName("id")
    private String orderId;
    //todo
    @SerializedName("createTime")
    private String createtime;
    @SerializedName("finishTime")
    private String finishtime;
    @SerializedName("grade")
    private String grade;
    @SerializedName("price")
    private Double price;
    @SerializedName("remark")
    private String remark;

    @SerializedName("state")
    private OrderState state;
    @SerializedName("recipientId")
    private String recipientId;
    @SerializedName("replacementId")
    private String replacementId;
    @SerializedName("expressName")
    private String expressName;
    @SerializedName("expressCode")
    private String expressCode;
    @SerializedName("pickupTime")
    private String pickupTime;
    @SerializedName("pickupAddress")
    private String pickupAddress;
    @SerializedName("pickupCode")
    private String pickupCode;
    @SerializedName("deliveryTime")
    private String deliveryTime;
    @SerializedName("deliveryAddress")
    private String deliveryAddress;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public void setReplacementId(String replacementId) {
        this.replacementId = replacementId;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public String getGrade() {
        return grade;
    }

    public Double getPrice() {
        return price;
    }

    public String getRemark() {
        return remark;
    }

    public OrderState getState() {
        return state;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getReplacementId() {
        return replacementId;
    }

    public String getExpressName() {
        return expressName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }



}
