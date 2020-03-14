package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于展示商品搜索结果的 VO
 * @author lkmc2
 * @date 2020/3/14 11:24
 */
@ApiModel("用于展示商品搜索结果的 VO 实体类")
@Data
public class SearchItemsVO {

    @ApiModelProperty(value = "商品id")
    private String itemId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "销售数量")
    private int sellCounts;

    @ApiModelProperty(value = "主图地址")
    private String imgUrl;

    @ApiModelProperty(value = "价格")
    private int price;

}
