package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 我的评论 VO（用户中心使用）
 * @author lkmc2
 * @date 2020/3/21 20:46
 */
@ApiModel("我的订单 VO 实体类（用户中心使用）")
@Data
public class MyCommentVO {

    @ApiModelProperty(value = "评论id")
    private String commentId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "商品id")
    private String itemId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "商品图片地址")
    private String itemImg;

}