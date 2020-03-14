package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.pojo.Items;
import com.lin.pojo.ItemsImg;
import com.lin.pojo.ItemsParam;
import com.lin.pojo.ItemsSpec;
import com.lin.service.ItemService;
import com.lin.utils.JsonResult;
import com.lin.utils.PagedGridResult;
import com.lin.vo.CommentLevelCountsVO;
import com.lin.vo.ItemInfoVO;
import com.lin.vo.ShopCartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品 Controller
 * @author lkmc2
 * @date 2020/3/10 22:51
 */
@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情")
    @GetMapping("/info/{itemId}")
    public JsonResult info(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId) {
        if (StrUtil.isBlank(itemId)) {
            return JsonResult.errorMsg(null);
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
        itemInfoVO.setItemImgList(itemsImgList);
        itemInfoVO.setItemSpecList(itemsSpecList);
        itemInfoVO.setItemParams(itemsParam);

        return JsonResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级")
    @GetMapping("/commentLevel")
    public JsonResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId) {
        if (StrUtil.isBlank(itemId)) {
            return JsonResult.errorMsg(null);
        }

        // 根据商品 id 查询商品的评价等级数量
        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);

        return JsonResult.ok(countsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论")
    @GetMapping("/comments")
    public JsonResult comments(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "评价等级")
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询的页数")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页每一页显示的条数")
            @RequestParam Integer pageSize) {

        if (StrUtil.isBlank(itemId)) {
            return JsonResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        // 分页查询商品评论列表
        PagedGridResult grid = itemService.queryPagedComments(itemId, level, page, pageSize);

        return JsonResult.ok(grid);
    }

    @ApiOperation(value = "通过关键字搜索商品列表", notes = "通过关键字搜索商品列表")
    @GetMapping("/search")
    public JsonResult search(
            @ApiParam(name = "keywords", value = "关键字", required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort", value = "排序")
            @RequestParam String sort,
            @ApiParam(name = "page", value = "查询的页数")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页每一页显示的条数")
            @RequestParam Integer pageSize) {

        if (StrUtil.isBlank(keywords)) {
            return JsonResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        // 分页查询商品评论列表
        PagedGridResult grid = itemService.searchItems(keywords, sort, page, pageSize);

        return JsonResult.ok(grid);
    }

    @ApiOperation(value = "通过分类id搜索商品列表", notes = "通过分类id搜索商品列表")
    @GetMapping("/catItems")
    public JsonResult catItems(
            @ApiParam(name = "catId", value = "三级分类id", required = true)
            @RequestParam String catId,
            @ApiParam(name = "sort", value = "排序")
            @RequestParam String sort,
            @ApiParam(name = "page", value = "查询的页数")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页每一页显示的条数")
            @RequestParam Integer pageSize) {

        if (StrUtil.isBlank(catId)) {
            return JsonResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        // 分页查询商品评论列表
        PagedGridResult grid = itemService.searchItemsByCatId(catId, sort, page, pageSize);

        return JsonResult.ok(grid);
    }

    @ApiOperation(value = "根据商品规格id查找最新的商品数据", notes = "根据商品规格id查找最新的商品数据，用于用户长时间未登录网站，刷新购物车中的数据（主要是商品价格）")
    @GetMapping("/refresh")
    public JsonResult refresh(
            @ApiParam(name = "itemSpecIds", value = "商品分类id，使用英文逗号拼接", required = true, example = "1001,1003,1005")
            @RequestParam String itemSpecIds) {

        if (StrUtil.isBlank(itemSpecIds)) {
            return JsonResult.ok();
        }

        // 购物车列表
        List<ShopCartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);

        return JsonResult.ok(list);
    }

}
