package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商户订单 VO
 * @author lkmc2
 * @date 2020/3/15 16:13
 */
@ApiModel("商户订单 VO 实体类")
@Data
public class MerchantOrdersVO {

    @ApiModelProperty(value = "商户订单号")
    private String merchantOrderId;

    @ApiModelProperty(value = "商户方的发起用户的用户主键id")
    private String merchantUserId;

    @ApiModelProperty(value = "实际支付总金额（包含商户所支付的订单费邮费总额）")
    private Integer amount;

    @ApiModelProperty(value = "支付方式 1:微信 2:支付宝")
    private Integer payMethod;

    @ApiModelProperty(value = "支付成功后的回调地址（自定义）")
    private String returnUrl;

}