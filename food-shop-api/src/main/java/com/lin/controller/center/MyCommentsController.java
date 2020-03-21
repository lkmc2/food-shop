package com.lin.controller.center;

import com.lin.controller.BaseController;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.OrderItems;
import com.lin.pojo.Orders;
import com.lin.service.center.MyCommentsService;
import com.lin.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 我的评价 Controller（用户中心使用）
 * @author lkmc2
 * @date 2020/3/21 11:16
 */
@Api(value = "我的评价模块（用户中心使用）", tags = "评价（用户中心使用）相关接口")
@RestController
@RequestMapping("mycomments")
public class MyCommentsController extends BaseController {

    @Autowired
    private MyCommentsService myCommentsService;

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表")
    @PostMapping("/pending")
    public JsonResult comments(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {

        // 用户验证用户和订单是否有关联关系，避免非法用户调用
        JsonResult checkResult = checkUserOrder(userId, orderId);

        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        Orders myOrder = (Orders) checkResult.getData();

        // 判断该笔订单是否已经评价，评价过了就不再继续
        if (YesOrNoEnum.YES.type.equals(myOrder.getIsComment())) {
            return JsonResult.errorMsg("该笔订单已经评价");
        }

        // 根据订单 id 查询关联的商品
        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);

        return JsonResult.ok(list);
    }

}
