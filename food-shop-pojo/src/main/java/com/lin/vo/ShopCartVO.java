package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 购物车信息
 * @author lkmc2
 * @date 2020/3/14 15:53
 */
@ApiModel(value = "购物车信息VO")
@Data
public class ShopCartVO {

    @ApiModelProperty(value = "商品id", example = "cake-1006", required = true)
    private String itemId;

    @ApiModelProperty(value = "商品图片地址", example = "http://122.152.205.72:88/foodie/cake-1006/img2.png", required = true)
    private String itemImgUrl;

    @ApiModelProperty(value = "商品名", example = "【天天吃货】机器猫最爱 铜锣烧 最美下午茶", required = true)
    private String itemName;

    @ApiModelProperty(value = "规格id", example = "cake-1006-spec-2", required = true)
    private String specId;

    @ApiModelProperty(value = "规则名称", example = "草莓水果", required = true)
    private String specName;

    @ApiModelProperty(value = "折扣价格", example = "10400", required = true)
    private String priceDiscount;

    @ApiModelProperty(value = "正常价格", example = "13000", required = true)
    private String priceNormal;

}
