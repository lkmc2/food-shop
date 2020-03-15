package com.lin.controller.center;

import com.lin.bo.center.CenterUserBO;
import com.lin.controller.BaseController;
import com.lin.pojo.Users;
import com.lin.service.center.CenterUserService;
import com.lin.utils.CookieUtils;
import com.lin.utils.JsonResult;
import com.lin.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息 Controller（用户中心使用）
 * @author lkmc2
 * @date 2020/3/15 21:15
 */
@Api(value = "用户信息接口（用户中心使用）", tags = "用户信息（用户中心使用）相关接口")
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping("/update")
    public JsonResult update(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @RequestBody CenterUserBO centerUserBO,
            HttpServletRequest request,
            HttpServletResponse response) {

        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);

        // 设置用户信息的敏感字段为空
        setNullProperty(userResult);

        // 设置 cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // todo：后续将增加令牌 token ，会整合进 redis ，分布式会话

        return JsonResult.ok(userResult);
    }

    /**
     * 设置用户信息的敏感字段为空
     * @param user 用户信息
     */
    private void setNullProperty(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        user.setBirthday(null);
    }

}
