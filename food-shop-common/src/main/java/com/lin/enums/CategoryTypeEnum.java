package com.lin.enums;

/**
 * 分类类型枚举
 * @author lkmc2
 * @date 2020/3/9 21:41
 */
public enum CategoryTypeEnum {
    /** 分类类型枚举 **/
    ROOT(1, "一级分类"),
    SECOND(2, "二级分类"),
    THIRD(3, "三级分类");

    public final Integer type;
    public final String value;

    CategoryTypeEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
