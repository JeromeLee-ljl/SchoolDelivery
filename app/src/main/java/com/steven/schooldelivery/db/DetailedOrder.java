package com.steven.schooldelivery.db;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 22340 on 2017/5/10.
 */

public class DetailedOrder extends Order  {
    // TODO: 2017/5/10 SerializedName
    @SerializedName("replacement")
    private User replacement;
    @SerializedName("recipient")
    private User recipient;

    public void setReplacement(User replacement) {
        this.replacement = replacement;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getReplacement() {

        return replacement;
    }

    public User getRecipient() {
        return recipient;
    }

    public static void saveAll(List<DetailedOrder> orders){
        DataSupport.deleteAll(DetailedOrder.class);
        for (DetailedOrder order : orders) {
            order.save();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" id:").append(getOrderId())
                .append(" createTime:").append(getCreatetime());
        // return builder.toString();
        return new Gson().toJson(this);
    }
}
