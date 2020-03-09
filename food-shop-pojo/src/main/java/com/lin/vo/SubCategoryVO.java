package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 三级分类 VO
 * @author lkmc2
 * @date 2020/3/9 22:07
 */
@ApiModel("三级分类 VO 实体类")
@Data
public class SubCategoryVO {

    @ApiModelProperty(value = "三级分类id")
    private Integer subId;

    @ApiModelProperty(value = "分类名称")
    private String subName;

    @ApiModelProperty(value = "分类类型", notes = "1.一级大分类 2.二级分类 3.三级小分类")
    private Integer subType;

    @ApiModelProperty(value = "父id，二级分类id")
    private Integer subFatherId;

}
