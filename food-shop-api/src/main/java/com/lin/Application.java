package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Spring Boot 启动类
 * @author lkmc2
 * @date 2020/3/7 14:02
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lin.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
