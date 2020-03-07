package com.lin.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品评价表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@Table(name = "item_comments")
public class ItemComments {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 用户id 用户名必须脱敏
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 商品名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 商品规格id 可为空
     */
    @Column(name = "item_spec_id")
    private String itemSpecId;

    /**
     * 规格名称 可为空
     */
    @Column(name = "spec_name")
    private String specName;

    /**
     * 评价等级 1：好评，2：中评，3：差评
     */
    @Column(name = "comment_level")
    private Integer commentLevel;

    /**
     * 评价内容
     */
    private String content;

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
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id 用户名必须脱敏
     *
     * @return user_id - 用户id 用户名必须脱敏
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id 用户名必须脱敏
     *
     * @param userId 用户id 用户名必须脱敏
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取商品id
     *
     * @return item_id - 商品id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置商品id
     *
     * @param itemId 商品id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商品名称
     *
     * @return item_name - 商品名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置商品名称
     *
     * @param itemName 商品名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取商品规格id 可为空
     *
     * @return item_spec_id - 商品规格id 可为空
     */
    public String getItemSpecId() {
        return itemSpecId;
    }

    /**
     * 设置商品规格id 可为空
     *
     * @param itemSpecId 商品规格id 可为空
     */
    public void setItemSpecId(String itemSpecId) {
        this.itemSpecId = itemSpecId;
    }

    /**
     * 获取规格名称 可为空
     *
     * @return spec_name - 规格名称 可为空
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * 设置规格名称 可为空
     *
     * @param specName 规格名称 可为空
     */
    public void setSpecName(String specName) {
        this.specName = specName;
    }

    /**
     * 获取评价等级 1：好评，2：中评，3：差评
     *
     * @return comment_level - 评价等级 1：好评，2：中评，3：差评
     */
    public Integer getCommentLevel() {
        return commentLevel;
    }

    /**
     * 设置评价等级 1：好评，2：中评，3：差评
     *
     * @param commentLevel 评价等级 1：好评，2：中评，3：差评
     */
    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    /**
     * 获取评价内容
     *
     * @return content - 评价内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评价内容
     *
     * @param content 评价内容
     */
    public void setContent(String content) {
        this.content = content;
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