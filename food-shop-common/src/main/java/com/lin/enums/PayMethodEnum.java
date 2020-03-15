package com.lin.enums;

/**
 * 支付方式枚举
 * @author lkmc2
 * @date 2020/3/14 20:37
 */
public enum PayMethodEnum {
    /** 支付方式枚举 **/
    WEIXIN(1, "微信"),
    ALIPAY(2, "支付宝");

    public final Integer type;
    public final String value;

    PayMethodEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
