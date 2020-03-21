package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 订单状态表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("订单状态表实体类")
@Data
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @Column(name = "order_id")
    @ApiModelProperty(value = "订单id 对应订单表的主键id")
    private String orderId;

    @Column(name = "order_status")
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @Column(name = "create_time")
    @ApiModelProperty(value = "订单创建时间 对应[10：待付款]状态")
    private Date createTime;

    @Column(name = "pay_time")
    @ApiModelProperty(value = "支付成功时间 对应[20：已付款，待发货]状态")
    private Date payTime;

    @Column(name = "deliver_time")
    @ApiModelProperty(value = "发货时间 对应[30：已发货，待收货]状态")
    private Date deliverTime;

    @Column(name = "success_time")
    @ApiModelProperty(value = "交易成功时间 对应[40：交易成功]状态")
    private Date successTime;

    @Column(name = "close_time")
    @ApiModelProperty(value = "交易关闭时间 对应[50：交易关闭]状态")
    private Date closeTime;

    @Column(name = "comment_time")
    @ApiModelProperty(value = "留言时间 用户在交易成功后的留言时间")
    private Date commentTime;

}