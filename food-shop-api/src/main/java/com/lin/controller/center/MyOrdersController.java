package com.lin.controller.center;

import cn.hutool.core.util.StrUtil;
import com.lin.controller.BaseController;
import com.lin.utils.JsonResult;
import com.lin.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
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
    public JsonResult deliver(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {

        if (StrUtil.isBlank(orderId)) {
            return JsonResult.errorMsg("订单ID不能为空");
        }

        // 将等待发货的订单的订单状态变更为商家发货
        myOrdersService.updateDeliverOrderStatus(orderId);

        return JsonResult.ok();
    }

    @ApiOperation(value = "用户确认收货", notes = "用户确认收货")
    @PostMapping("/confirmReceive")
    public JsonResult confirmReceive(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {

        JsonResult checkResult = checkUserOrder(userId, orderId);

        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 更新订单状态 -> 确认收货
        boolean isSuccess = myOrdersService.updateReceiveOrderStatus(orderId);

        if (!isSuccess) {
            return JsonResult.errorMsg("订单确认收货失败");
        }

        return JsonResult.ok();
    }

    @ApiOperation(value = "用户删除订单", notes = "用户删除订单")
    @PostMapping("/delete")
    public JsonResult delete(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {

        JsonResult checkResult = checkUserOrder(userId, orderId);

        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 删除订单（逻辑删除）
        boolean isSuccess = myOrdersService.deleteOrder(userId, orderId);

        if (!isSuccess) {
            return JsonResult.errorMsg("订单删除失败");
        }

        return JsonResult.ok();
    }

}
