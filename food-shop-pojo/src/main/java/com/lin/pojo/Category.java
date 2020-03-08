package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 商品分类实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("商品分类实体类")
@Data
public class Category {

    @Id
    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类类型")
    private String type;

    @Column(name = "father_id")
    @ApiModelProperty(value = "父id")
    private String fatherId;

    @ApiModelProperty(value = "主键id")
    private String logo;

    @ApiModelProperty(value = "图标")
    private String slogan;

    @Column(name = "cat_image")
    @ApiModelProperty(value = "口号")
    private String catImage;

    @Column(name = "bg_color")
    @ApiModelProperty(value = "背景颜色")
    private String bgColor;

}