package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

/**
 * 用户表实体类 VO
 * @author lkmc2
 * @date 2020/4/12 18:18
 */
@ApiModel("用户表实体类 VO")
@Data
public class UsersVO {

    @Id
    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "头像")
    private String face;

    @ApiModelProperty(value = "性别 1：男，0：女，2：保密")
    private Integer sex;

    @ApiModelProperty(value = "用户会话token")
    private String userUniqueToken;

}