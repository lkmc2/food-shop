package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 轮播图实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("轮播图实体类")
@Data
public class Carousel {

    @Id
    @ApiModelProperty(value = "主键id")
    private String id;

    @Column(name = "image_url")
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @Column(name = "background_color")
    @ApiModelProperty(value = "背景色")
    private String backgroundColor;

    @Column(name = "item_id")
    @ApiModelProperty(value = "商品id")
    private String itemId;

    @Column(name = "cat_id")
    @ApiModelProperty(value = "商品分类id")
    private String catId;

    @ApiModelProperty(value = "轮播图类型", notes = "用于判断，可根据场频id或者分类进行页面跳转，1：商品，2：分类")
    private Integer type;

    @ApiModelProperty(value = "轮播图展示顺序")
    private Integer sort;

    @Column(name = "is_show")
    @ApiModelProperty(value = "是否展示")
    private Integer isShow;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}