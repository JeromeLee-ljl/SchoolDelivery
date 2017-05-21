package com.steven.schooldelivery.http.gson;

import com.google.gson.annotations.SerializedName;
import com.steven.schooldelivery.db.Order;

/**
 * Created by 22340 on 2017/5/10.
 */

public class DetailedOrder extends Order{
    // TODO: 2017/5/10 SerializedName
    @SerializedName("replacementName")
    private String replacementName;
    @SerializedName("replacementPhone")
    private String replacementPhone;
    @SerializedName("recipientName")
    private String recipientName;
    @SerializedName("recipientPhone")
    private String recipientPhone;

    public void setReplacementName(String replacementName) {
        this.replacementName = replacementName;
    }

    public void setReplacementPhone(String replacementPhone) {
        this.replacementPhone = replacementPhone;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getReplacementName() {
        return replacementName;
    }

    public String getReplacementPhone() {
        return replacementPhone;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("\nId:").append(getOrderId())
                .append("\ncreatetime:").append(getCreatetime())
                .append("\nfinishtime:").append(getFinishtime())
                .append("\ngrade:").append(getGrade())
                .append("\ncost:").append(getPrice())
                .append("\nremark:").append(getRemark())
                .append("\nstate:").append(getState())
                .append("\nrecipientId:").append(getRecipientId())
                .append("\nreplacementId:").append(getReplacementId())
                .append("\nexpressName:").append(getExpressName())
                .append("\nexpressCode:").append(getExpressCode())
                .append("\npickupTime:").append(getPickupTime())
                .append("\npickupAddress:").append(getPickupAddress())
                .append("\npickupCode:").append(getPickupCode())
                .append("\ndeliveryTime:").append(getDeliveryTime())
                .append("\ndeliveryAddress:").append(getDeliveryAddress())
                .append("\nreplacementName:").append(getReplacementName())
                .append("\nreplacementPhone:").append(getRecipientPhone())
                .append("\nrecipientName:").append(getRecipientName())
                .append("\nrecipientPhone:").append(getRecipientPhone());
        return builder.toString();
    }
}
