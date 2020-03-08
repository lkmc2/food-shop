package com.lin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 示范 Controller
 * @author lkmc2
 * @date 2020/3/7 14:04
 */
@ApiIgnore
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() {
        logger.debug("debug: hello~");
        logger.info("info: hello~");
        logger.warn("warn: hello~");
        logger.error("error: hello~");

        return "Hello World";
    }

    @GetMapping("/setSession")
    public Object setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 设置 session 信息
        session.setAttribute("userInfo", "new user");
        // 设置过期时长
        session.setMaxInactiveInterval(3600);

        // 获取 session 信息
        Object userInfo = session.getAttribute("userInfo");
        logger.info("userInfo = 【{}】", userInfo);

        // 移除 session 信息
        session.removeAttribute("userInfo");

        return "ok";
    }

}
