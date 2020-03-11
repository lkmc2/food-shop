package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于展示商品评价数量的 VO
 * @author lkmc2
 * @date 2020/3/11 20:45
 */
@ApiModel("用于展示商品评价数量的 VO 实体类")
@Data
public class CommentLevelCountsVO {

    @ApiModelProperty(value = "所有评价条数")
    private Integer totalCounts;

    @ApiModelProperty(value = "好评条数")
    private Integer goodCounts;

    @ApiModelProperty(value = "中评条数")
    private Integer normalCounts;

    @ApiModelProperty(value = "差评条数")
    private Integer badCounts;

}
