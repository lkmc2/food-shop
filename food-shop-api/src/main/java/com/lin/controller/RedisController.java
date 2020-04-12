package com.lin.controller;

import com.lin.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

/**
 * Redis Controller
 * @author lkmc2
 * @date 2020/4/5 11:26
 */
@ApiIgnore
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisOperator redisOperator;

    @GetMapping("/set")
    public String set(String key, String value) {
        redisOperator.set(key, value);
        return "OK";
    }

    @GetMapping("/get")
    public String get(String key) {
        return redisOperator.get(key);
    }

    @GetMapping("/delete")
    public String delete(String key) {
        redisOperator.del(key);
        return "OK";
    }

    /**
     * 批量查询 mget
     * @param keys 多个关键字
     * @return 关键字对应的值列表
     */
    @GetMapping("/mget")
    public List<String> mget(String... keys) {
        return redisOperator.mget(Arrays.asList(keys));
    }

}
