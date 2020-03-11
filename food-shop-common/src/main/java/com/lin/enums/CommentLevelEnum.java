package com.lin.enums;

/**
 * 评论级别
 * @author lkmc2
 * @date 2020/3/11 20:53
 */
public enum CommentLevelEnum {
    /** 好评 **/
    GOOD(1, "好评"),
    /** 中评 **/
    NORMAL(2, "中评"),
    /** 差评 **/
    BAD(3, "差评");

    public final Integer type;
    public final String value;

    CommentLevelEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
