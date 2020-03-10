package com.lin.vo;

import com.lin.pojo.Items;
import com.lin.pojo.ItemsImg;
import com.lin.pojo.ItemsParam;
import com.lin.pojo.ItemsSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品详情 VO
 * @author lkmc2
 * @date 2020/3/10 23:01
 */
@ApiModel("商品详情 VO 实体类")
@Data
public class ItemInfoVO {

    @ApiModelProperty(value = "商品详情")
    private Items item;

    @ApiModelProperty(value = "商品图片列表")
    private List<ItemsImg> itemsImgList;

    @ApiModelProperty(value = "商品规格列表")
    private List<ItemsSpec> itemsSpecList;

    @ApiModelProperty(value = "商品参数")
    private ItemsParam itemsParam;

}
