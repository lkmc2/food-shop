package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 六个最新商品的简单数据类型 VO
 * @author lkmc2
 * @date 2020/3/9 23:01
 */
@ApiModel("六个最新商品的简单数据类型 VO 实体类")
@Data
public class SimpleItemVO {

    @ApiModelProperty(value = "商品id")
    private String itemId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "商品url")
    private String itemUrl;

}
