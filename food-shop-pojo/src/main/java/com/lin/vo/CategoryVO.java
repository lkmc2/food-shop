package com.lin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 二级分类 VO
 * @author lkmc2
 * @date 2020/3/9 22:02
 */
@ApiModel("二级分类 VO 实体类")
@Data
public class CategoryVO {

    @ApiModelProperty(value = "二级分类id")
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类类型", notes = "1.一级大分类 2.二级分类 3.三级小分类")
    private Integer type;

    @ApiModelProperty(value = "父id，一级分类id")
    private Integer fatherId;

    @ApiModelProperty(value = "三级分类列表")
    private List<SubCategoryVO> subCatList;

}
