package com.lin.enums;

/**
 * 订单状态枚举
 * @author lkmc2
 * @date 2020/3/15 10:58
 */
public enum OrderStatusEnum {
    /** 订单状态枚举 **/
    WAIT_PAY(10, "待付款"),
    WAIT_DELIVER(20, "已付款，待发货"),
    WAIT_RECEIVE(30, "已发货，待收货"),
    SUCCESS(40, "交易成功"),
    CLOSE(50, "交易关闭");

    public final Integer type;
    public final String value;

    OrderStatusEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
