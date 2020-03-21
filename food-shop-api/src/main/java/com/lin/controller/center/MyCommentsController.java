package com.lin.controller.center;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.lin.bo.center.OrderItemsCommentBO;
import com.lin.controller.BaseController;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.OrderItems;
import com.lin.pojo.Orders;
import com.lin.service.center.MyCommentsService;
import com.lin.utils.JsonResult;
import com.lin.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "保存评论列表", notes = "保存评论列表")
    @PostMapping("/saveList")
    public JsonResult saveList(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @RequestBody List<OrderItemsCommentBO> commentList) {

        // 用户验证用户和订单是否有关联关系，避免非法用户调用
        JsonResult checkResult = checkUserOrder(userId, orderId);

        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        if (CollUtil.isEmpty(commentList)) {
            return JsonResult.errorMsg("评论内容列表不能为空");
        }

        // 保存评论列表
        myCommentsService.saveComments(orderId, userId, commentList);

        return JsonResult.ok();
    }

    @ApiOperation(value = "查询我的评论列表", notes = "查询我的评论列表")
    @PostMapping("/query")
    public JsonResult query(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
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

        // 分页查询评论列表
        PagedGridResult grid = myCommentsService.queryMyComments(userId, page, pageSize);

        return JsonResult.ok(grid);
    }

}
