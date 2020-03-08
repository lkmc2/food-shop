package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.bo.UserBO;
import com.lin.pojo.Users;
import com.lin.service.UserService;
import com.lin.utils.JsonResult;
import com.lin.utils.Md5Utils;
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

    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 1.判断用户名和密码非空
        if (StrUtil.isBlank(username) ||
                StrUtil.isBlank(password)) {
            return JsonResult.errorMsg("用户名或密码不能为空");
        }

        // 2.实现登陆
        Users userResult = userService.queryUserForLogin(username, Md5Utils.getMd5Str(password));

        if (userResult == null) {
            return JsonResult.errorMsg("用户名或密码不正确");
        }

        return JsonResult.ok(userResult);
    }

}
