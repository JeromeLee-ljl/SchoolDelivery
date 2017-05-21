package com.steven.schooldelivery.entity;

import java.sql.Timestamp;

/**
 * Created by finderlo on 2017/4/7.
 */
 
 
public class OrdersOperationLogEntity {
    private String ordersId;
    private String stateType;
    private Timestamp stateChangeTime;
    private int ordersLogId;

     
     
    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

     
     
    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

     
     
    public Timestamp getStateChangeTime() {
        return stateChangeTime;
    }

    public void setStateChangeTime(Timestamp stateChangeTime) {
        this.stateChangeTime = stateChangeTime;
    }

     
     
    public int getOrdersLogId() {
        return ordersLogId;
    }

    public void setOrdersLogId(int ordersLogId) {
        this.ordersLogId = ordersLogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersOperationLogEntity that = (OrdersOperationLogEntity) o;

        if (ordersLogId != that.ordersLogId) return false;
        if (ordersId != null ? !ordersId.equals(that.ordersId) : that.ordersId != null) return false;
        if (stateType != null ? !stateType.equals(that.stateType) : that.stateType != null) return false;
        if (stateChangeTime != null ? !stateChangeTime.equals(that.stateChangeTime) : that.stateChangeTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ordersId != null ? ordersId.hashCode() : 0;
        result = 31 * result + (stateType != null ? stateType.hashCode() : 0);
        result = 31 * result + (stateChangeTime != null ? stateChangeTime.hashCode() : 0);
        result = 31 * result + ordersLogId;
        return result;
    }
}
