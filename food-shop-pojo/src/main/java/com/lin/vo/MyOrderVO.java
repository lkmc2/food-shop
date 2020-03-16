package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 我的订单 VO（用户中心使用）
 * @author lkmc2
 * @date 2020/3/15 16:13
 */
@ApiModel("我的订单 VO 实体类（用户中心使用）")
@Data
public class MyOrderVO {

    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "支付方式")
    private Integer payMethod;

    @ApiModelProperty(value = "实际支付价格")
    private Integer realPayAmount;

    @ApiModelProperty(value = "邮费价格")
    private Integer postAmount;

    @ApiModelProperty(value = "用户是否评论")
    private Integer isComment;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "子订单列表")
    private List<MySubOrderItemVO> subOrderItemList;

}