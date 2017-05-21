package com.steven.schooldelivery.entity;

/**
 * Created by finderlo on 2017/4/7.
 */
 
 
public class PaymentInfomationEntity {
    private String paymentId;
    private String ordersId;
    private String paymentType;
    private Double paymentCost;
    private String paymentState;
    private String paymentChannels;
    private String paymentInformation;

     
     
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

     
     
    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

     
     
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

     
     
    public Double getPaymentCost() {
        return paymentCost;
    }

    public void setPaymentCost(Double paymentCost) {
        this.paymentCost = paymentCost;
    }

     
     
    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

     
     
    public String getPaymentChannels() {
        return paymentChannels;
    }

    public void setPaymentChannels(String paymentChannels) {
        this.paymentChannels = paymentChannels;
    }

     
     
    public String getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(String paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentInfomationEntity that = (PaymentInfomationEntity) o;

        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) return false;
        if (ordersId != null ? !ordersId.equals(that.ordersId) : that.ordersId != null) return false;
        if (paymentType != null ? !paymentType.equals(that.paymentType) : that.paymentType != null) return false;
        if (paymentCost != null ? !paymentCost.equals(that.paymentCost) : that.paymentCost != null) return false;
        if (paymentState != null ? !paymentState.equals(that.paymentState) : that.paymentState != null) return false;
        if (paymentChannels != null ? !paymentChannels.equals(that.paymentChannels) : that.paymentChannels != null)
            return false;
        if (paymentInformation != null ? !paymentInformation.equals(that.paymentInformation) : that.paymentInformation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentId != null ? paymentId.hashCode() : 0;
        result = 31 * result + (ordersId != null ? ordersId.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (paymentCost != null ? paymentCost.hashCode() : 0);
        result = 31 * result + (paymentState != null ? paymentState.hashCode() : 0);
        result = 31 * result + (paymentChannels != null ? paymentChannels.hashCode() : 0);
        result = 31 * result + (paymentInformation != null ? paymentInformation.hashCode() : 0);
        return result;
    }
}
