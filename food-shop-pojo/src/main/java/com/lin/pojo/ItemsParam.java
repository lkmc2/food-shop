package com.lin.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品参数实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@Table(name = "items_param")
public class ItemsParam {
    /**
     * 商品参数id
     */
    @Id
    private String id;

    /**
     * 商品外键id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 产地 如：中国江苏
     */
    @Column(name = "produce_place")
    private String producePlace;

    /**
     * 保质期 如：180天
     */
    @Column(name = "foot_period")
    private String footPeriod;

    /**
     * 品牌名 如：红蜻蜓
     */
    private String brand;

    /**
     * 生产厂名 如：红蜻蜓工厂
     */
    @Column(name = "factory_name")
    private String factoryName;

    /**
     * 生产厂地址 如：红蜻蜓生产基地
     */
    @Column(name = "factory_address")
    private String factoryAddress;

    /**
     * 包装方式 如：袋装
     */
    @Column(name = "packaging_method")
    private String packagingMethod;

    /**
     * 规格重量 如：35g
     */
    private String weight;

    /**
     * 存储方法 如：常温5至25度
     */
    @Column(name = "storage_method")
    private String storageMethod;

    /**
     * 食用方式
     */
    @Column(name = "eat_method")
    private String eatMethod;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取商品参数id
     *
     * @return id - 商品参数id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置商品参数id
     *
     * @param id 商品参数id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商品外键id
     *
     * @return item_id - 商品外键id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品外键id
     *
     * @param itemId 商品外键id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取产地 如：中国江苏
     *
     * @return produce_place - 产地 如：中国江苏
     */
    public String getProducePlace() {
        return producePlace;
    }

    /**
     * 设置产地 如：中国江苏
     *
     * @param producePlace 产地 如：中国江苏
     */
    public void setProducePlace(String producePlace) {
        this.producePlace = producePlace;
    }

    /**
     * 获取保质期 如：180天
     *
     * @return foot_period - 保质期 如：180天
     */
    public String getFootPeriod() {
        return footPeriod;
    }

    /**
     * 设置保质期 如：180天
     *
     * @param footPeriod 保质期 如：180天
     */
    public void setFootPeriod(String footPeriod) {
        this.footPeriod = footPeriod;
    }

    /**
     * 获取品牌名 如：红蜻蜓
     *
     * @return brand - 品牌名 如：红蜻蜓
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌名 如：红蜻蜓
     *
     * @param brand 品牌名 如：红蜻蜓
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取生产厂名 如：红蜻蜓工厂
     *
     * @return factory_name - 生产厂名 如：红蜻蜓工厂
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * 设置生产厂名 如：红蜻蜓工厂
     *
     * @param factoryName 生产厂名 如：红蜻蜓工厂
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     * 获取生产厂地址 如：红蜻蜓生产基地
     *
     * @return factory_address - 生产厂地址 如：红蜻蜓生产基地
     */
    public String getFactoryAddress() {
        return factoryAddress;
    }

    /**
     * 设置生产厂地址 如：红蜻蜓生产基地
     *
     * @param factoryAddress 生产厂地址 如：红蜻蜓生产基地
     */
    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

    /**
     * 获取包装方式 如：袋装
     *
     * @return packaging_method - 包装方式 如：袋装
     */
    public String getPackagingMethod() {
        return packagingMethod;
    }

    /**
     * 设置包装方式 如：袋装
     *
     * @param packagingMethod 包装方式 如：袋装
     */
    public void setPackagingMethod(String packagingMethod) {
        this.packagingMethod = packagingMethod;
    }

    /**
     * 获取规格重量 如：35g
     *
     * @return weight - 规格重量 如：35g
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置规格重量 如：35g
     *
     * @param weight 规格重量 如：35g
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 获取存储方法 如：常温5至25度
     *
     * @return storage_method - 存储方法 如：常温5至25度
     */
    public String getStorageMethod() {
        return storageMethod;
    }

    /**
     * 设置存储方法 如：常温5至25度
     *
     * @param storageMethod 存储方法 如：常温5至25度
     */
    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    /**
     * 获取食用方式
     *
     * @return eat_method - 食用方式
     */
    public String getEatMethod() {
        return eatMethod;
    }

    /**
     * 设置食用方式
     *
     * @param eatMethod 食用方式
     */
    public void setEatMethod(String eatMethod) {
        this.eatMethod = eatMethod;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}