package com.lin.enums;

/**
 * 性别枚举
 * @author lkmc2
 * @date 2020/3/8 13:43
 */
public enum SexEnum {
    /** 女 **/
    WOMAN(0, "女"),
    /** 男 **/
    MAN(1, "男"),
    /** 保密 **/
    SECRET(2, "保密");

    public final Integer type;
    public final String value;

    SexEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
