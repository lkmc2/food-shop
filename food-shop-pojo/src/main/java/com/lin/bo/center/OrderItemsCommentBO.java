package com.lin.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单商品评论（用户中心使用）
 * @author lkmc2
 * @date 2020/3/21 11:38
 */
@ApiModel(value = "订单商品评论（用户中心使用）BO", description = "从客户端，由客户传入的数据封装在此实体类中")
@Data
public class OrderItemsCommentBO {

    @ApiModelProperty(value = "评论id", example = "1", required = true)
    private String commentId;

    @ApiModelProperty(value = "商品id", example = "1", required = true)
    private String itemId;

    @ApiModelProperty(value = "商品名称", example = "马克杯")
    private String itemName;

    @ApiModelProperty(value = "商品规格id", example = "1")
    private String itemSpecId;

    @ApiModelProperty(value = "商品规格名称", example = "黑色")
    private String itemSpecName;

    @ApiModelProperty(value = "评论等级", example = "1：好评，2：中评，3：差评")
    private Integer commentLevel;

    @ApiModelProperty(value = "评论内容", example = "很满意")
    private String content;

}
