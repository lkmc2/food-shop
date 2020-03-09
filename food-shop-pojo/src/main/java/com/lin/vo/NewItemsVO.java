package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 最新商品 VO
 * @author lkmc2
 * @date 2020/3/9 22:58
 */
@ApiModel("最新商品 VO 实体类")
@Data
public class NewItemsVO {

    @ApiModelProperty(value = "一级分类id")
    private Integer rootCatId;

    @ApiModelProperty(value = "以及分类名称")
    private String rootCatName;

    @ApiModelProperty(value = "口号")
    private String slogan;

    @ApiModelProperty(value = "分类图片")
    private String catImage;

    @ApiModelProperty(value = "背景色")
    private String bgColor;

    @ApiModelProperty(value = "六个最新商品的简单数据类型列表")
    private List<SimpleItemVO> simpleItemList;

}
