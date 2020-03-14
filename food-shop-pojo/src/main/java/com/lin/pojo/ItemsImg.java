package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品图片实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("商品图片实体类")
@Data
@Table(name = "items_img")
public class ItemsImg {

    @Id
    @ApiModelProperty(value = "图片主键id")
    private String id;

    @Column(name = "item_id")
    @ApiModelProperty(value = "商品外键id")
    private String itemId;

    @ApiModelProperty(value = "图片地址")
    private String url;

    @ApiModelProperty(value = "图片顺序", notes = "从小到大")
    private String sort;

    @Column(name = "is_main")
    @ApiModelProperty(value = "是否主图", notes = "1：是，0：否")
    private Integer isMain;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}