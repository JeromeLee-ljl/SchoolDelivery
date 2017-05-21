package com.steven.schooldelivery.http;

/**
 * Created by 22340 on 2017/4/22.
 */

public class HttpUrl {
    public static final String SERVER_URL = "http://sed.ticknick.me";

    //——登陆
    public static final String LOGIN = SERVER_URL + "/tokens";
    //——注册
    public static final String SIGN_UP = SERVER_URL + "/users";
    //——get获取用户信息
    //——put修改用户信息
    public static final String USER_INFO = SERVER_URL + "/users/token";
    //——创建订单
    public static final String ORDER_CREATE = SERVER_URL + "/orders";
    //post 升级
    public static final String UPGRADE = SERVER_URL + "/reviews";
    //get 获取所有订单
    public static final String GET_ALL_ORDER = SERVER_URL + "/orders/token";






    //用户
    //——查找用户
    public static final String USER_FIND = SERVER_URL + "/user/find";


    //——升级
    public static final String USER_UPGRADE = SERVER_URL + "/user/upgrade";
    //——降级
    public static final String USER_DEGRADE = SERVER_URL + "/user/degrade";


    //订单
    //——检查能否创建
    public static final String ORDER_CREATE_CHECK = SERVER_URL + "/order/check_create";

    // //返回这个用户的没有完成的订单，作为收件人/代取人
    // public static final String ORDER_FIND_BY_USER_UNCOMPLETE = SERVER_URL + "/order/find_by_user_uncomplete";
    // //返回这个用户的完成的订单，作为收件人/代取人
    // public static final String ORDER_FIND_BY_USER_COMPLETE = SERVER_URL + "/order/find_by_user_complete";
    // //查询详细订单
    // public static final String ORDER_FIND_DETAILED_ORDER = SERVER_URL + "/order/find_order_user";
    // //查询订单状态
    // public static final String ORDER_FIND_ORDER_LOG = SERVER_URL+ "/order/find_order_log.json";
    // //——申述
    public static final String ORDER_COMPLAIN = SERVER_URL + "/complains";





    //——创建保证金订单
    public static final String ORDER_CREATE_DEPOSIT = SERVER_URL + "/order/create/deposit";
    //——创建收件订单
    public static final String ORDER_CREATE_NORMAL = SERVER_URL + "/order/create/normal";
    //——查询订单  网页
    public static final String ORDER_FIND = SERVER_URL + "/order/find";
    //——查询Timeline订单
    public static final String ORDER_TIMELINE = SERVER_URL + "/order/timeline";

    //——接取订单
    public static final String ORDER_ACCEPT = SERVER_URL + "/order/accept";
    //——接取快递
    public static final String ORDER_DELIVERY = SERVER_URL + "/order/delivery";
    //——确认订单
    public static final String ORDER_CONFIRM = SERVER_URL + "/order/confirm";
    //——评价
    public static final String ORDER_COMMENT = SERVER_URL + "/order/comment";

    //——取消
    public static final String ORDER_CANCEL = SERVER_URL + "/order/cancel";


    //资金
    //——支付
    public static final String FUNDS_PAY = "/funds/pay";
    //——提取酬劳
    public static final String FUNDS_WITHDRAW = "/funds/withdraw";


    //消息
    //——消息查看
    public static final String MESSAGE_TIMELINE = "/message/timeline";


}
