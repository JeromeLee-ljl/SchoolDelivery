package com.steven.schooldelivery.entity;

/**
 * 等待接单、已接单、已收件、已确认、待评价、已完成、已取消、申诉中、已赔偿
 *
 * @author finderlo
 * @date 17/04/2017
 */
public enum OrderState {
    // -1 代表完成订单

    WAIT_PAY("等待支付"),

    //等待接单
    WAIT_ACCEPT("等待接单"),

    //已接单
    ACCEPTED("已接单"),

    //已收件，待送达
    TAKE_PARCEL_WAIT_DELIVERY("已收件，待送达"),

    //已确认
    AFFIRMATIVE("已确认"),

    //待评价 -1
    WAIT_COMMENT("待评价"),

    //已完成 -1
    COMPLETED("已完成"),

    //已取消
    CANCELED("已取消"),

    //申诉中
    COMPLAINING("申诉中"),

    //已赔偿 -1
    COMPENSATION("已赔偿");

    private String type;

    public boolean isComplete() {
        switch (this) {
            case WAIT_COMMENT:
            case COMPLETED:
            case COMPENSATION:
                return true;
            default:
                return false;
        }
    }

    OrderState(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
