package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.service.UserService;
import com.lin.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证服务 Controller
 * @author lkmc2
 * @date 2020/3/7 22:04
 */
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

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

}
