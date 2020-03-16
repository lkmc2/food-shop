package com.lin.controller.center;

import cn.hutool.core.util.StrUtil;
import com.lin.controller.BaseController;
import com.lin.service.center.MyOrdersService;
import com.lin.utils.JsonResult;
import com.lin.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 我的订单 Controller（用户中心使用）
 * @author lkmc2
 * @date 2020/3/16 21:54
 */
@Api(value = "我的订单（用户中心使用）", tags = "我的订单（用户中心使用）相关接口")
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController {

    @Autowired
    private MyOrdersService myOrdersService;

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表")
    @PostMapping("/query")
    public JsonResult comments(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderStatus", value = "订单状态")
            @RequestParam Integer orderStatus,
            @ApiParam(name = "page", value = "查询的页数")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页每一页显示的条数")
            @RequestParam Integer pageSize) {

        if (StrUtil.isBlank(userId)) {
            return JsonResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        // 分页查询订单列表
        PagedGridResult grid = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);

        return JsonResult.ok(grid);
    }

    @ApiOperation(value = "商家发货", notes = "商家发货")
    @GetMapping("/deliver")
    public JsonResult comments(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {

        if (StrUtil.isBlank(orderId)) {
            return JsonResult.errorMsg("订单ID不能为空");
        }

        // 将等待发货的订单的订单状态变更为商家发货
        myOrdersService.updateDeliverOrderStatus(orderId);

        return JsonResult.ok();
    }

}
