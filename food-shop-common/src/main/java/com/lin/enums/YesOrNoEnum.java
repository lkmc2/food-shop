package com.lin.enums;

/**
 * 是否枚举
 * @author lkmc2
 * @date 2020/3/9 21:13
 */
public enum YesOrNoEnum {
    /** 是否枚举 **/
    NO(0, "否"),
    YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNoEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
