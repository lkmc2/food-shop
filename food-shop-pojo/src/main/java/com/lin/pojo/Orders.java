package com.lin.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 订单表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
public class Orders {
    /**
     * 订单主键 同时也是订单编号
     */
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 收货人快照
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人手机号快照
     */
    @Column(name = "receiver_mobile")
    private String receiverMobile;

    /**
     * 收货地址快照
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 订单总价格
     */
    @Column(name = "total_amount")
    private Integer totalAmount;

    /**
     * 实际支付总价格
     */
    @Column(name = "real_pay_amount")
    private Integer realPayAmount;

    /**
     * 邮费 默认可以为零，代表包邮
     */
    @Column(name = "post_amount")
    private Integer postAmount;

    /**
     * 支付方式
     */
    @Column(name = "pay_method")
    private Integer payMethod;

    /**
     * 买家留言
     */
    @Column(name = "left_msg")
    private String leftMsg;

    /**
     * 拓展字段
     */
    private String extend;

    /**
     * 买家是否评价 1：已评价，0：未评价
     */
    @Column(name = "is_comment")
    private Integer isComment;

    /**
     * 逻辑删除状态 1：删除，0：未删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

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
     * 获取订单主键 同时也是订单编号
     *
     * @return id - 订单主键 同时也是订单编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置订单主键 同时也是订单编号
     *
     * @param id 订单主键 同时也是订单编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取收货人快照
     *
     * @return receiver_name - 收货人快照
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货人快照
     *
     * @param receiverName 收货人快照
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取收货人手机号快照
     *
     * @return receiver_mobile - 收货人手机号快照
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * 设置收货人手机号快照
     *
     * @param receiverMobile 收货人手机号快照
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    /**
     * 获取收货地址快照
     *
     * @return receiver_address - 收货地址快照
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 设置收货地址快照
     *
     * @param receiverAddress 收货地址快照
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    /**
     * 获取订单总价格
     *
     * @return total_amount - 订单总价格
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置订单总价格
     *
     * @param totalAmount 订单总价格
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取实际支付总价格
     *
     * @return real_pay_amount - 实际支付总价格
     */
    public Integer getRealPayAmount() {
        return realPayAmount;
    }

    /**
     * 设置实际支付总价格
     *
     * @param realPayAmount 实际支付总价格
     */
    public void setRealPayAmount(Integer realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    /**
     * 获取邮费 默认可以为零，代表包邮
     *
     * @return post_amount - 邮费 默认可以为零，代表包邮
     */
    public Integer getPostAmount() {
        return postAmount;
    }

    /**
     * 设置邮费 默认可以为零，代表包邮
     *
     * @param postAmount 邮费 默认可以为零，代表包邮
     */
    public void setPostAmount(Integer postAmount) {
        this.postAmount = postAmount;
    }

    /**
     * 获取支付方式
     *
     * @return pay_method - 支付方式
     */
    public Integer getPayMethod() {
        return payMethod;
    }

    /**
     * 设置支付方式
     *
     * @param payMethod 支付方式
     */
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 获取买家留言
     *
     * @return left_msg - 买家留言
     */
    public String getLeftMsg() {
        return leftMsg;
    }

    /**
     * 设置买家留言
     *
     * @param leftMsg 买家留言
     */
    public void setLeftMsg(String leftMsg) {
        this.leftMsg = leftMsg;
    }

    /**
     * 获取拓展字段
     *
     * @return extend - 拓展字段
     */
    public String getExtend() {
        return extend;
    }

    /**
     * 设置拓展字段
     *
     * @param extend 拓展字段
     */
    public void setExtend(String extend) {
        this.extend = extend;
    }

    /**
     * 获取买家是否评价 1：已评价，0：未评价
     *
     * @return is_comment - 买家是否评价 1：已评价，0：未评价
     */
    public Integer getIsComment() {
        return isComment;
    }

    /**
     * 设置买家是否评价 1：已评价，0：未评价
     *
     * @param isComment 买家是否评价 1：已评价，0：未评价
     */
    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    /**
     * 获取逻辑删除状态 1：删除，0：未删除
     *
     * @return is_delete - 逻辑删除状态 1：删除，0：未删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置逻辑删除状态 1：删除，0：未删除
     *
     * @param isDelete 逻辑删除状态 1：删除，0：未删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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