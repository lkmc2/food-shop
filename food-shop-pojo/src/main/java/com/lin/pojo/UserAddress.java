package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户地址表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("用户地址表实体类")
@Data
@Table(name = "user_address")
public class UserAddress {

    @Id
    @ApiModelProperty(value = "地址主键id")
    private String id;

    @Column(name = "user_id")
    @ApiModelProperty(value = "关联用户id")
    private String userId;

    @ApiModelProperty(value = "收件人姓名")
    private String receiver;

    @ApiModelProperty(value = "收件人手机号")
    private String mobile;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "县区")
    private String district;

    @ApiModelProperty(value = "详细地址")
    private String detail;

    @ApiModelProperty(value = "拓展字段")
    private String extend;

    @Column(name = "is_default")
    @ApiModelProperty(value = "是否默认地址")
    private Integer isDefault;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}