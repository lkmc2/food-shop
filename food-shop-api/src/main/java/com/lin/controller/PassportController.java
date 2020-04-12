package com.lin.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.lin.bo.ShopCartBO;
import com.lin.bo.UserBO;
import com.lin.pojo.Users;
import com.lin.service.UserService;
import com.lin.utils.*;
import com.lin.vo.UsersVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 认证服务 Controller
 * @author lkmc2
 * @date 2020/3/7 22:04
 */
@Api(value = "注册登录", tags = {"用于注册登陆的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redisOperator;

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
    public JsonResult register(@RequestBody UserBO userBO,
                               HttpServletRequest request,
                               HttpServletResponse response) {
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
        Users userResult = userService.createUser(userBO);

        // 设置用户信息的敏感字段为空
//        setNullProperty(userResult);

        // 实现用户的 redis 会话，保存会话到 redis
        UsersVO usersVO = convertUsersVO(userResult);

        // 设置 cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(usersVO), true);

        // todo：生成用户token，存入redis会话
        // 同步购物车数据
        syncShopCartData(userResult.getId(), request, response);

        return JsonResult.ok();
    }

    /**
     * 转换用户对象为用户 VO 对象
     * @param users 用户对象
     * @return 用户 VO 对象
     */
    private UsersVO convertUsersVO(Users users) {
        // 实现用户的 redis 会话，保存会话到 redis
        String uniqueToken = IdUtil.simpleUUID();
        redisOperator.set(REDIS_USER_TOKEN + ":" + users.getId(), uniqueToken);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(users, usersVO);
        usersVO.setUserUniqueToken(uniqueToken);

        return usersVO;
    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserBO userBO,
                            HttpServletRequest request,
                            HttpServletResponse response) {
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

        // 设置用户信息的敏感字段为空
//        setNullProperty(userResult);

        // 实现用户的 redis 会话，保存会话到 redis
        UsersVO usersVO = convertUsersVO(userResult);

        // 设置 cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(usersVO), true);

        // 同步购物车数据
        syncShopCartData(userResult.getId(), request, response);

        return JsonResult.ok(userResult);
    }

    /**
     * 注册登陆成功后，同步 cookie 和 redis 中的购物车数据
     * @param userId 用户 id
     * @param request 请求
     * @param response 响应
     */
    private void syncShopCartData(String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        /*
        1.redis 中无数据，①如果 cookie 中的购物车为空，name这个时候不做任何处理；
                          ②如果 cookie 中的购物车不为空，此时直接放入 redis 中；
        2.redis 中有数据，①如果 cookie 中的购物车为空，那么直接把 redis 的购物车覆盖本地  cookie；
                          ②如果 cookie 中的购物车不为空，如果 cookie 中的某个商品在 redis 中存在，
                          则以 cookie 为主，删除 redis 中的商品，把 cookie 中的商品直接覆盖 redis 中的商品
        3.同步到了 redis 中后，覆盖本地 cookie 购物车的数据，保证本地购物车的数据是同步的
         */

        // 从 redis 中获取购物车
        String shopCartJsonRedis = redisOperator.get(FOOD_SHOP_SHOP_CART + ":" + userId);

        // 从 cookie 中获取购物车
        String shopCartStrCookie = CookieUtils.getCookieValue(request, FOOD_SHOP_SHOP_CART, true);

        if (StrUtil.isBlank(shopCartJsonRedis)) {
            if (StrUtil.isNotBlank(shopCartStrCookie)) {
                // redis 为空，cookie 不为空，直接把 cookie 中的数据放入 redis
                redisOperator.set(FOOD_SHOP_SHOP_CART + ":" + userId, shopCartStrCookie);
            }
        } else {
            if (StrUtil.isNotBlank(shopCartStrCookie)) {
                // redis 不为空，cookie 不为空，合并 cookie 和 redis 终点购物车商品数据（同一商品则覆盖 redis）
                /*
                 1.已经存在的，把 cookie 中对应的数量，覆盖 redis
                 2.该项商品标记为待删除，同一放入一个待删除的 List
                 3.从 cookie 中清除所有的待删除 List
                 4.合并  redis 和 cookie 中的数据
                 5.合并到 redis 和 cookie 中
                 */

                // redis 购物车列表
                List<ShopCartBO> shopCartRedisList = JsonUtils.jsonToList(shopCartJsonRedis, ShopCartBO.class);

                // cookie 购物车列表
                List<ShopCartBO> shopCartCookieList = JsonUtils.jsonToList(shopCartJsonRedis, ShopCartBO.class);

                // 待删除购物车列表
                List<ShopCartBO> pendingDeleteList = Lists.newArrayList();

                for (ShopCartBO redisShopCart : shopCartRedisList) {
                    String redisSpecId = redisShopCart.getSpecId();

                    for (ShopCartBO cookieShopCart : shopCartCookieList) {
                        String cookieSpecId = cookieShopCart.getSpecId();

                        if (redisSpecId.equals(cookieSpecId)) {
                            // 同一商品，使用 cookie 中的购买数量覆盖 redis 中的购买数量，不累加
                            redisShopCart.setBuyCounts(cookieShopCart.getBuyCounts());

                            // 把该项 cookie 中的购物车放入待删除列表，用于最后的删除与合并
                            pendingDeleteList.add(cookieShopCart);
                        }
                    }
                }

                // 从现有 cookie 中删除对应的覆盖过的商品数据
                shopCartCookieList.removeAll(pendingDeleteList);

                // 合并两个 List
                shopCartRedisList.addAll(shopCartCookieList);

                // 更新到 redis 和 cookie
                redisOperator.set(FOOD_SHOP_SHOP_CART + ":" + userId, JsonUtils.objectToJson(shopCartRedisList));
                CookieUtils.setCookie(request, response, FOOD_SHOP_SHOP_CART, JsonUtils.objectToJson(shopCartRedisList), true);
            } else {
                // redis 不为空，cookie 为空，直接把 redis 覆盖 cookie
                CookieUtils.setCookie(request, response, FOOD_SHOP_SHOP_CART, shopCartJsonRedis, true);
            }
        }

    }

    @ApiOperation(value = "用户退出登陆", notes = "用户退出登陆")
    @PostMapping("/logout")
    public JsonResult logout(@RequestParam String userId,
                             HttpServletRequest request,
                             HttpServletResponse response){
        // 清除用户相关的 cookie 信息
        CookieUtils.deleteCookie(request, response, "user");

        // 用户退出登陆，清除 redis 中的用户会话信息
        redisOperator.del(REDIS_USER_TOKEN + ":" + userId);

        // 用户退出登陆，需要清空购物车
        // 分布式会话中需要清除用户数据
        CookieUtils.deleteCookie(request, response, FOOD_SHOP_SHOP_CART);

        return JsonResult.ok();
    }

}
