package com.steven.schooldelivery.entity;

import java.sql.Timestamp;

/**
 * Created by finderlo on 2017/4/7.
 */
 
 
public class OrdersEntity {
    private String ordersId;
    private Timestamp ordersCreatetime;
    private Timestamp ordersFinishtime;
    private String ordersGrade;
    private Double ordersCost;
    private String ordersRemark;

    private OrderState ordersState;

    private String recipientId;
    private String replacementId;
    private String expressName;
    private String expressCode;
    private Timestamp pickupTime;
    private String pickupAddress;
    private String pickupCode;
    private Timestamp deliveryTime;
    private String deliveryAddress;

     
     
    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

     
     
    public Timestamp getOrdersCreatetime() {
        return ordersCreatetime;
    }

    public void setOrdersCreatetime(Timestamp ordersCreatetime) {
        this.ordersCreatetime = ordersCreatetime;
    }

     
     
    public Timestamp getOrdersFinishtime() {
        return ordersFinishtime;
    }

    public void setOrdersFinishtime(Timestamp ordersFinishtime) {
        this.ordersFinishtime = ordersFinishtime;
    }

     
     
    public String getOrdersGrade() {
        return ordersGrade;
    }

    public void setOrdersGrade(String ordersGrade) {
        this.ordersGrade = ordersGrade;
    }

     
     
    public Double getOrdersCost() {
        return ordersCost;
    }

    public void setOrdersCost(Double ordersCost) {
        this.ordersCost = ordersCost;
    }

     
     
    public String getOrdersRemark() {
        return ordersRemark;
    }

    public void setOrdersRemark(String ordersRemark) {
        this.ordersRemark = ordersRemark;
    }

     
     
    public OrderState getOrdersState() {
        return ordersState;
    }

    public void setOrdersState(OrderState ordersState) {
        this.ordersState = ordersState;
    }

     
     
    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

     
     
    public String getReplacementId() {
        return replacementId;
    }

    public void setReplacementId(String replacementId) {
        this.replacementId = replacementId;
    }

     
     
    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

     
     
    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

     
     
    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }

     
     
    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

     
     
    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }

     
     
    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

     
     
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (ordersId != null ? !ordersId.equals(that.ordersId) : that.ordersId != null) return false;
        if (ordersCreatetime != null ? !ordersCreatetime.equals(that.ordersCreatetime) : that.ordersCreatetime != null)
            return false;
        if (ordersFinishtime != null ? !ordersFinishtime.equals(that.ordersFinishtime) : that.ordersFinishtime != null)
            return false;
        if (ordersGrade != null ? !ordersGrade.equals(that.ordersGrade) : that.ordersGrade != null) return false;
        if (ordersCost != null ? !ordersCost.equals(that.ordersCost) : that.ordersCost != null) return false;
        if (ordersRemark != null ? !ordersRemark.equals(that.ordersRemark) : that.ordersRemark != null) return false;
        if (ordersState != null ? !ordersState.equals(that.ordersState) : that.ordersState != null) return false;
        if (recipientId != null ? !recipientId.equals(that.recipientId) : that.recipientId != null) return false;
        if (replacementId != null ? !replacementId.equals(that.replacementId) : that.replacementId != null)
            return false;
        if (expressName != null ? !expressName.equals(that.expressName) : that.expressName != null) return false;
        if (expressCode != null ? !expressCode.equals(that.expressCode) : that.expressCode != null) return false;
        if (pickupTime != null ? !pickupTime.equals(that.pickupTime) : that.pickupTime != null) return false;
        if (pickupAddress != null ? !pickupAddress.equals(that.pickupAddress) : that.pickupAddress != null)
            return false;
        if (pickupCode != null ? !pickupCode.equals(that.pickupCode) : that.pickupCode != null) return false;
        if (deliveryTime != null ? !deliveryTime.equals(that.deliveryTime) : that.deliveryTime != null) return false;
        if (deliveryAddress != null ? !deliveryAddress.equals(that.deliveryAddress) : that.deliveryAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ordersId != null ? ordersId.hashCode() : 0;
        result = 31 * result + (ordersCreatetime != null ? ordersCreatetime.hashCode() : 0);
        result = 31 * result + (ordersFinishtime != null ? ordersFinishtime.hashCode() : 0);
        result = 31 * result + (ordersGrade != null ? ordersGrade.hashCode() : 0);
        result = 31 * result + (ordersCost != null ? ordersCost.hashCode() : 0);
        result = 31 * result + (ordersRemark != null ? ordersRemark.hashCode() : 0);
        result = 31 * result + (ordersState != null ? ordersState.hashCode() : 0);
        result = 31 * result + (recipientId != null ? recipientId.hashCode() : 0);
        result = 31 * result + (replacementId != null ? replacementId.hashCode() : 0);
        result = 31 * result + (expressName != null ? expressName.hashCode() : 0);
        result = 31 * result + (expressCode != null ? expressCode.hashCode() : 0);
        result = 31 * result + (pickupTime != null ? pickupTime.hashCode() : 0);
        result = 31 * result + (pickupAddress != null ? pickupAddress.hashCode() : 0);
        result = 31 * result + (pickupCode != null ? pickupCode.hashCode() : 0);
        result = 31 * result + (deliveryTime != null ? deliveryTime.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        return result;
    }
}
