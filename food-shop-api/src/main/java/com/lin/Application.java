package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Spring Boot 启动类
 * @author lkmc2
 * @date 2020/3/7 14:02
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lin.dao")
@ComponentScan(basePackages = {"com.lin", "org.n3r.idworker"})
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
