package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Spring Boot 启动类
 * @author lkmc2
 * @date 2020/3/7 14:02
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan(basePackages = "com.lin.dao")
@ComponentScan(basePackages = {"com.lin", "org.n3r.idworker"})
@EnableScheduling // 开启定时任务
@EnableRedisHttpSession // 开启使用 redis 作为 spring session
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
