package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 订单表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("订单表实体类")
@Data
public class Orders {

    @Id
    @ApiModelProperty(value = "订单主键 同时也是订单编号")
    private String id;

    @Column(name = "user_id")
    @ApiModelProperty(value = "用户id")
    private String userId;

    @Column(name = "receiver_name")
    @ApiModelProperty(value = "收货人快照")
    private String receiverName;

    @Column(name = "receiver_mobile")
    @ApiModelProperty(value = "收货人手机号快照")
    private String receiverMobile;

    @Column(name = "receiver_address")
    @ApiModelProperty(value = "收货地址快照")
    private String receiverAddress;

    @Column(name = "total_amount")
    @ApiModelProperty(value = "订单总价格")
    private Integer totalAmount;

    @Column(name = "real_pay_amount")
    @ApiModelProperty(value = "实际支付总价格")
    private Integer realPayAmount;

    @Column(name = "post_amount")
    @ApiModelProperty(value = "邮费", notes = "默认可以为零，代表包邮")
    private Integer postAmount;

    @Column(name = "pay_method")
    @ApiModelProperty(value = "支付方式")
    private Integer payMethod;

    @Column(name = "left_msg")
    @ApiModelProperty(value = "买家留言")
    private String leftMsg;

    @ApiModelProperty(value = "拓展字段")
    private String extend;

    @Column(name = "is_comment")
    @ApiModelProperty(value = "买家是否评价", notes = "1：已评价，0：未评价")
    private Integer isComment;

    @Column(name = "is_delete")
    @ApiModelProperty(value = "逻辑删除状态", notes = "1：删除，0：未删除")
    private Integer isDelete;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}