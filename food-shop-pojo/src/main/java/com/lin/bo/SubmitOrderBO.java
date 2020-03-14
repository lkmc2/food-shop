package com.lin.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 提交的订单
 * @author lkmc2
 * @date 2020/3/14 20:27
 */
@ApiModel(value = "提交的订单BO")
@Data
public class SubmitOrderBO {

    @ApiModelProperty(value = "用户id", example = "1", required = true)
    private String userId;

    @ApiModelProperty(value = "商品规格id", example = "1", required = true)
    private String itemSpecIds;

    @ApiModelProperty(value = "用户地址id", example = "190825CG3AA14Y3C", required = true)
    private String addressId;

    @ApiModelProperty(value = "支付方式", example = "1", required = true)
    private Integer payMethod;

    @ApiModelProperty(value = "买家留言", example = "不加辣", required = true)
    private String leftMsg;

}
