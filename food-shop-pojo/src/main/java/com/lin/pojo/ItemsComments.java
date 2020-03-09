package com.lin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品评价表实体类
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
@ApiModel("商品评价表实体类")
@Data
@Table(name = "items_comments")
public class ItemsComments {

    @Id
    @ApiModelProperty(value = "主键id")
    private String id;

    @Column(name = "user_id")
    @ApiModelProperty(value = "用户名必须脱敏")
    private String userId;

    @Column(name = "item_id")
    @ApiModelProperty(value = "商品id")
    private String itemId;

    @Column(name = "item_name")
    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @Column(name = "item_spec_id")
    @ApiModelProperty(value = "商品规格id", notes = "可为空")
    private String itemSpecId;

    @Column(name = "spec_name")
    @ApiModelProperty(value = "规格名称", notes = "可为空")
    private String specName;

    @Column(name = "comment_level")
    @ApiModelProperty(value = "评价等级", notes = "1：好评，2：中评，3：差评")
    private Integer commentLevel;

    @ApiModelProperty(value = "评价内容")
    private String content;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}