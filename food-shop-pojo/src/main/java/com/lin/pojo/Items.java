package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("商品表实体类")
@Data
public class Items {

    @Id
    @ApiModelProperty(value = "商品主键id")
    private String id;

    @Column(name = "item_name")
    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @Column(name = "cat_id")
    @ApiModelProperty(value = "分类外键id")
    private Integer catId;

    @Column(name = "root_cat_id")
    @ApiModelProperty(value = "一级分类文件id")
    private Integer rootCatId;

    @Column(name = "sell_counts")
    @ApiModelProperty(value = "累计销量")
    private Integer sellCounts;

    @Column(name = "on_off_status")
    @ApiModelProperty(value = "上下架状态", notes = "1：上架，2：下架")
    private String onOffStatus;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "商品内容")
    private String content;

}