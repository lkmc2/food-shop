package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品参数实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("商品参数实体类")
@Data
@Table(name = "items_param")
public class ItemsParam {

    @Id
    @ApiModelProperty(value = "商品参数id")
    private String id;

    @Column(name = "item_id")
    @ApiModelProperty(value = "商品外键id")
    private String itemId;

    /**
     * 产地 如：中国江苏
     */
    @Column(name = "produce_place")
    @ApiModelProperty(value = "图片主键id")
    private String producePlace;

    @Column(name = "foot_period")
    @ApiModelProperty(value = "保质期", notes = "如：180天")
    private String footPeriod;

    /**
     * 品牌名 如：红蜻蜓
     */
    @ApiModelProperty(value = "图片主键id")
    private String brand;

    /**
     * 生产厂名 如：红蜻蜓工厂
     */
    @Column(name = "factory_name")
    @ApiModelProperty(value = "图片主键id")
    private String factoryName;

    /**
     * 生产厂地址 如：红蜻蜓生产基地
     */
    @Column(name = "factory_address")
    @ApiModelProperty(value = "图片主键id")
    private String factoryAddress;

    /**
     * 包装方式 如：袋装
     */
    @Column(name = "packaging_method")
    @ApiModelProperty(value = "图片主键id")
    private String packagingMethod;

    /**
     * 规格重量 如：35g
     */
    @ApiModelProperty(value = "图片主键id")
    private String weight;

    /**
     * 存储方法 如：常温5至25度
     */
    @Column(name = "storage_method")
    @ApiModelProperty(value = "图片主键id")
    private String storageMethod;

    @Column(name = "eat_method")
    @ApiModelProperty(value = "食用方式")
    private String eatMethod;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}