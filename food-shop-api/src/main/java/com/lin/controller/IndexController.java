package com.lin.controller;

import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.Carousel;
import com.lin.pojo.Category;
import com.lin.service.CarouselService;
import com.lin.service.CategoryService;
import com.lin.utils.JsonResult;
import com.lin.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表")
    @GetMapping("/carousel")
    public JsonResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNoEnum.YES.type);
        return JsonResult.ok(list);
    }

    /**
     * 首页分类展示需求
     * 1.第一次刷新主页查询大分类，渲染展示到首页
     * 2.如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "获取商品分类（一级分类）", notes = "获取商品分类（一级分类）")
    @GetMapping("/cats")
    public JsonResult cats() {
        List<Category> list = categoryService.queryAllRootLevelCat();
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

        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return JsonResult.ok(list);
    }

}
