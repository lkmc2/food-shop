package com.lin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * War 包启动类
 * @author lkmc2
 * @date 2020/3/22 11:19
 */
public class WarStarterApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向 Spring Boot 启动类
        return builder.sources(Application.class);
    }

}
