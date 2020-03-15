package com.lin.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 用户信息（用户中心使用）
 * @author lkmc2
 * @date 2020/3/8 12:05
 */
@ApiModel(value = "用户信息（用户中心使用）BO", description = "从客户端，由客户传入的数据封装在此实体类中")
@Data
public class CenterUserBO {

    @ApiModelProperty(value = "用户名", example = "jack", required = true)
    private String username;

    @ApiModelProperty(value = "密码", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", example = "123456", required = true)
    private String confirmPassword;

    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 12, message = "用户昵称不能超过12位")
    @ApiModelProperty(value = "用户昵称", example = "jack")
    private String nickname;

    @Length(max = 12, message = "用户昵称不能超过12位")
    @ApiModelProperty(value = "真实姓名", example = "jack")
    private String realname;

    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "手机号格式不正确")
    @ApiModelProperty(value = "手机号", example = "13777778888")
    private String mobile;

    @Email(message = "邮箱号格式不正确")
    @ApiModelProperty(value = "邮箱地址", example = "abc@163.com")
    private String email;

    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 0, message = "性别选择不正确")
    @ApiModelProperty(value = "性别", example = "0", notes = "0：女 1：男 2：保密")
    private Integer sex;

    @ApiModelProperty(value = "生日", example = "2000-01-01")
    private Date birthday;

}
