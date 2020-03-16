package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 我的子订单 VO（用户中心使用）
 * @author lkmc2
 * @date 2020/3/16 21:36
 */
@ApiModel("我的订单 VO 实体类（用户中心使用）")
@Data
public class MySubOrderItemVO {

    @ApiModelProperty(value = "订单号")
    private String itemId;

    @ApiModelProperty(value = "订单图片地址")
    private String itemImg;

    @ApiModelProperty(value = "订单名称")
    private String itemName;

    @ApiModelProperty(value = "订单规格id")
    private String itemSpecId;

    @ApiModelProperty(value = "订单规格名称")
    private String itemSpecName;

    @ApiModelProperty(value = "购买数量")
    private Integer buyCounts;

    @ApiModelProperty(value = "价格")
    private Integer price;

}
