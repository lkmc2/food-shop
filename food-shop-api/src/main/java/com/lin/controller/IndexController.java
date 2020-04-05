package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.Carousel;
import com.lin.pojo.Category;
import com.lin.service.CarouselService;
import com.lin.service.CategoryService;
import com.lin.utils.JsonResult;
import com.lin.utils.JsonUtils;
import com.lin.utils.RedisOperator;
import com.lin.vo.CategoryVO;
import com.lin.vo.NewItemsVO;
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
 * 首页 Controller
 * @author lkmc2
 * @date 2020/3/9 21:10
 */
@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表")
    @GetMapping("/carousel")
    public JsonResult carousel() {
        // 查询缓存
        String carouselStr = redisOperator.get("carousel");

        List<Carousel> list;

        if (StrUtil.isBlank(carouselStr)) {
            // 从数据库中查询，并设置到缓存中
            list = carouselService.queryAll(YesOrNoEnum.YES.type);
            redisOperator.set("carousel", JsonUtils.objectToJson(list));
        } else {
            // 直接加载缓存
            list = JsonUtils.jsonToList(carouselStr, Carousel.class);
        }

        return JsonResult.ok(list);
    }

    /**
     1.后台运行系统，一旦广告（轮播图）发生更改，就可以删除缓存，然后重置
     2.定时重置，比如每天凌晨三点重置
     3.每个轮播图都有可能是一个广告，每个广告都会有一个过期时间，过期了，再重置
     */

    /**
     * 首页分类展示需求
     * 1.第一次刷新主页查询大分类，渲染展示到首页
     * 2.如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "获取商品分类（一级分类）", notes = "获取商品分类（一级分类）")
    @GetMapping("/cats")
    public JsonResult cats() {
        // 查询缓存
        String catsStr = redisOperator.get("cats");

        List<Category> list;

        if (StrUtil.isBlank(catsStr)) {
            // 从数据库中查询，并设置到缓存中
            list = categoryService.queryAllRootLevelCat();
            redisOperator.set("cats", JsonUtils.objectToJson(list));
        } else {
            // 直接加载缓存
            list = JsonUtils.jsonToList(catsStr, Category.class);
        }

        return JsonResult.ok(list);
    }

    @ApiOperation(value = "获取商品分类子分类（二级和三级分类）", notes = "获取商品分类子分类（二级和三级分类）")
    @GetMapping("/subCat/{rootCatId}")
    public JsonResult subCats(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable("rootCatId") Integer rootCatId) {
        if (rootCatId == null) {
            return JsonResult.errorMsg("分类不存在");
        }

        // 查询缓存
        String catsStr = redisOperator.get("subCat:" + rootCatId);

        List<CategoryVO> list;

        if (StrUtil.isBlank(catsStr)) {
            // 从数据库中查询，并设置到缓存中
            list = categoryService.getSubCatList(rootCatId);
            redisOperator.set("subCat:" + rootCatId, JsonUtils.objectToJson(list));
        } else {
            // 直接加载缓存
            list = JsonUtils.jsonToList(catsStr, CategoryVO.class);
        }

        return JsonResult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JsonResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable("rootCatId") Integer rootCatId) {
        if (rootCatId == null) {
            return JsonResult.errorMsg("分类不存在");
        }

        // 查询缓存
        String sixNewItemsStr = redisOperator.get("sixNewItems:" + rootCatId);

        List<NewItemsVO> list;

        if (StrUtil.isBlank(sixNewItemsStr)) {
            // 从数据库中查询，并设置到缓存中
            list = categoryService.getSixNewItemLazy(rootCatId);
            redisOperator.set("sixNewItems:" + rootCatId, JsonUtils.objectToJson(list));
        } else {
            // 直接加载缓存
            list = JsonUtils.jsonToList(sixNewItemsStr, NewItemsVO.class);
        }

        return JsonResult.ok(list);
    }

}
