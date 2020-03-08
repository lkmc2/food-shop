package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.bo.UserBO;
import com.lin.service.UserService;
import com.lin.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 认证服务 Controller
 * @author lkmc2
 * @date 2020/3/7 22:04
 */
@Api(value = "注册登录", tags = {"用于注册登陆的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 用户名是否存在
     */
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在")
    @GetMapping("/usernameIsExist")
    public JsonResult usernameIsExist(@RequestParam String username) {
        // 1.判断用户名不能为空
        if (StrUtil.isBlank(username)) {
            return JsonResult.errorMsg("用户名不能为空");
        }

        // 2.查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return JsonResult.errorMsg("用户名已经存在");
        }

        // 3.请求成功，用户名没有重复
        return JsonResult.ok();
    }

    /**
     * 注册新用户
     * @param userBO 用户信息
     * @return 是否注册成功
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public JsonResult register(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        // 1.判断用户名和密码非空
        if (StrUtil.isBlank(username) ||
                StrUtil.isBlank(password) ||
                StrUtil.isBlank(confirmPassword)) {
            return JsonResult.errorMsg("用户名或密码不能为空");
        }

        // 2.查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return JsonResult.errorMsg("用户名已存在");
        }

        // 3.密码长度不能少于6位
        if (StrUtil.length(password) < 6) {
            return JsonResult.errorMsg("密码长度不能少于6位");
        }

        // 4.判断两次密码是否一致
        if (!StrUtil.equals(password, confirmPassword)) {
            return JsonResult.errorMsg("两次密码输入不一致");
        }

        // 5.实现注册
        userService.createUser(userBO);

        return JsonResult.ok();
    }

}
