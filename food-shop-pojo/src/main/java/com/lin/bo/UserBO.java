package com.lin.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息
 * @author lkmc2
 * @date 2020/3/8 12:05
 */
@ApiModel(value = "用户对象BO", description = "从客户端，由客户传入的数据封装在此实体类中")
public class UserBO {
    @ApiModelProperty(value = "用户名", example = "jack", required = true)
    private String username;
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    private String password;
    @ApiModelProperty(value = "确认密码", example = "123456")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserBO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

}
