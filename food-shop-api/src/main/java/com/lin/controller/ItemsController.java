package com.lin.controller;

import com.lin.pojo.Items;
import com.lin.pojo.ItemsImg;
import com.lin.pojo.ItemsParam;
import com.lin.pojo.ItemsSpec;
import com.lin.service.ItemService;
import com.lin.utils.JsonResult;
import com.lin.vo.ItemInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品 Controller
 * @author lkmc2
 * @date 2020/3/10 22:51
 */
@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情")
    @GetMapping("/info/{itemId}")
    public JsonResult info(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId) {
        if (itemId == null) {
            JsonResult.errorMsg(null);
        }

        // 根据商品 id 查询商品详情
        Items item = itemService.queryItemById(itemId);
        // 根据商品 id 查询商品图片列表
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        // 根据商品 id 查询商品规格列表
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        // 根据商品 id 查询商品参数
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemsImgList(itemsImgList);
        itemInfoVO.setItemsSpecList(itemsSpecList);
        itemInfoVO.setItemsParam(itemsParam);

        return JsonResult.ok(itemInfoVO);
    }


}
