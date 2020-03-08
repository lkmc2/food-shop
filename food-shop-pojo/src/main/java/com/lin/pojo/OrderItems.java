package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单商品关联表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("订单商品关联表实体类")
@Data
@Table(name = "order_items")
public class OrderItems {

    @Id
    @ApiModelProperty(value = "主键id")
    private String id;

    @Column(name = "order_id")
    @ApiModelProperty(value = "归属订单id")
    private String orderId;

    @Column(name = "item_id")
    @ApiModelProperty(value = "商品id")
    private String itemId;

    @Column(name = "item_img")
    @ApiModelProperty(value = "商品图片")
    private String itemImg;

    @Column(name = "item_name")
    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @Column(name = "item_spec_id")
    @ApiModelProperty(value = "规格id")
    private String itemSpecId;

    @Column(name = "item_spec_name")
    @ApiModelProperty(value = "规格名称")
    private String itemSpecName;

    @ApiModelProperty(value = "成交价格")
    private Integer price;

    @Column(name = "buy_counts")
    @ApiModelProperty(value = "购买数量")
    private String buyCounts;

}