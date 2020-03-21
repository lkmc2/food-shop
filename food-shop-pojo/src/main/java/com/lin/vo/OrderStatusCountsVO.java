package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单状态概览数量 VO
 * @author lkmc2
 * @date 2020/3/21 21:18
 */
@ApiModel("订单状态概览数量 VO 实体类")
@Data
public class OrderStatusCountsVO {

    @ApiModelProperty(value = "等待支付订单数")
    private Integer waitPayCounts;

    @ApiModelProperty(value = "等待发货订单数")
    private Integer waitDeliverCounts;

    @ApiModelProperty(value = "等待收货订单数")
    private Integer waitReceiveCounts;

    @ApiModelProperty(value = "等待评论订单数")
    private Integer waitCommentCounts;

}