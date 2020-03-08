package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品规格实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("商品规格实体类")
@Data
@Table(name = "items_spec")
public class ItemsSpec {

    @Id
    @ApiModelProperty(value = "商品规格id")
    private String id;

    @Column(name = "item_id")
    @ApiModelProperty(value = "商品外键id")
    private String itemId;

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "折扣力度")
    private BigDecimal discounts;

    @Column(name = "price_discount")
    @ApiModelProperty(value = "优惠价")
    private Integer priceDiscount;

    @Column(name = "price_normal")
    @ApiModelProperty(value = "原价")
    private Integer priceNormal;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}