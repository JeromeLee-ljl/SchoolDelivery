package com.steven.schooldelivery;

/**
 * 定义常量
 */
public class Constant {

    public static class SharedPreferences{
        public static final String USERINFO = "userInfo";

        public static final String TOKEN = "token";
        public static final String ID = "userId";
        public static final String PHONE = "phone";
        public static final String PASSWORD = "password";
    }

    public static class HttpParam {

        public static class Tokens {
            public static final String UID = "uid";
            public static final String PHONE = "phone";
            public static final String PASSWORD = "password";
            public static final String TOKEN = "token";
        }
        public static class Users{
            public static final String SCHOLLCARD = "school_card";
            public static final String PASSWORD = "password";
            public static final String PHONE = "phone";
            public static final String SCHOOLADDRESS = "school_address";
            public static final String SEX = "sex";
            public static final String NAME = "name";
            public static final String SCHOOLNAME = "school_name";

            public static final String ALIPAY = "alipay";
            public static final String IDCARD = "id_card";
            public static final String PHOTO = "photo";
            public static final String TOKEN = "token";
        }

        public static class Orders{
            public static final String TOKEN = "token";
            public static final String EXPRESSNAME = "express_name";
            public static final String PICKUPADDRESS = "pickup_address";
            public static final String PICKUPTIME = "pickup_time";
            public static final String DELIVERYADDRESS = "delivery_address";
            public static final String DELIVERYTIME = "delivery_time";
            public static final String PRICE = "price";
            public static final String EXPRESSCODE = "express_code";
            public static final String PICKUPCODE = "pickup_code";
            public static final String ORDERREMARK = "order_remark";
        }
    }


    // public static final String EVENT_ORDER_CANCEL_TYPE = "event_order_cancel_type";
    // public static final String EVENT_ORDER_CANCEL_TYPE_RECEIVER = "event_order_cancel_type_receiver";
    // public static final String EVENT_ORDER_CANCEL_TYPE_REPLACEMENT = "event_order_cancel_type_replacement";

    public static final String TOKEN = "token";

    //user
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "user_phone";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_SCHOOLCARD = "user_schoolcard";
    public static final String USER_SEX = "user_sex";
    public static final String USER_SCHOOLNAME = "user_schoolname";
    public static final String USER_SCHOOLADDRESS = "user_schooladdress";

    // public static final String USER_TYPE = "user_type";
    //
    // public static final String USER_ENTITY = "user_entity_object";


    //order
    public static final String EXPRESS_NAME = "express_name";
    public static final String EXPRESS_CODE = "express_code";
    public static final String PICKUP_ADDRESS = "pickup_address";
    public static final String PICKUP_TIME = "pickup_time";
    public static final String PICKUP_CODE = "pickup_code";
    public static final String DELIVERY_ADDRESS = "delivery_address";
    public static final String DELIVERY_TIME = "delivery_time";
    public static final String ORDERS_REMARK = "order_remark";
    public static final String ORDERS_PRICE = "order_price";


    // public static final String ORDER_ID = "order_id";
    // public static final String ORDER_CANCEL_TYPE = "order_cancel_type";
    // public static final String ORDER_CANCEL_TYPE_RECEIVER = "order_cancel_type_rec";
    // public static final String ORDER_CANCEL_TYPE_REPLACEMENT = "order_cancel_type_rep";
    // public static final String ORDER_CANCEL_REASON = "order_cancel_reason";
    // public static final String ORDER_ATTR_GRADLE = "order_attr_gradle";
    // public static final String ORDER_ATTR_STATE = "order_attr_state";
    // public static final String ORDER_ATTR_COMPLETE_STATE = "order_attr__complete_state";
    //
    // public static final String ACTION_SUB_TYPE = "action_sub_type";
    //
    // public static final String MANUAL_UPGRADE = "action_sub_type_upgrade";
    // public static final String MANUAL_DEGRADE = "action_sub_type_degrade";
    // public static final String MANUAL_COMPLAIN = "action_sub_type_complain";
}