package com.lin.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户新增或者修改地址的 BO
 * @author lkmc2
 * @date 2020/3/14 17:25
 */
@ApiModel(value = "用户新增或者修改地址的BO")
@Data
public class AddressBO {

    @ApiModelProperty(value = "地址id", example = "1001", required = true)
    private String addressId;

    @ApiModelProperty(value = "用户id", example = "1", required = true)
    private String userId;

    @ApiModelProperty(value = "收件人", example = "jack", required = true)
    private String receiver;

    @ApiModelProperty(value = "手机号", example = "13722222222", required = true)
    private String mobile;

    @ApiModelProperty(value = "省份", example = "广西省", required = true)
    private String province;

    @ApiModelProperty(value = "城市", example = "南宁", required = true)
    private String city;

    @ApiModelProperty(value = "县区", example = "横县", required = true)
    private String district;

    @ApiModelProperty(value = "详细地址", example = "东方村22号", required = true)
    private String detail;

}
