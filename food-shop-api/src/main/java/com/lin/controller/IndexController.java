package com.lin.controller;

import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.Carousel;
import com.lin.service.CarouselService;
import com.lin.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表")
    @GetMapping("/carousel")
    public JsonResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNoEnum.YES.type);
        return JsonResult.ok(list);
    }

}
