package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用于展示商品评价的 VO
 * @author lkmc2
 * @date 2020/3/11 20:45
 */
@ApiModel("用于展示商品评价的 VO 实体类")
@Data
public class ItemCommentVO {

    @ApiModelProperty(value = "评论级别", notes = "1：好评，2：中评，3：差评")
    private Integer commentLevel;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "用户头像地址")
    private String userFace;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

}
