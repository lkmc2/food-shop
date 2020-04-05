package com.lin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Redis Controller
 * @author lkmc2
 * @date 2020/4/5 11:26
 */
@SuppressWarnings("unchecked")
@ApiIgnore
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/set")
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "OK";
    }

    @GetMapping("/get")
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/delete")
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

}
