package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单 VO
 * @author lkmc2
 * @date 2020/3/15 16:13
 */
@ApiModel("订单 VO 实体类")
@Data
public class OrderVO {

    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "商户订单 VO")
    private MerchantOrdersVO merchantOrdersVO;

}