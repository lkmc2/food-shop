package com.lin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * cors 跨域请求配置类
 * @author lkmc2
 * @date 2020/3/8 16:26
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1.添加 cors 配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域访问的网址
        config.addAllowedOrigin("http://localhost:8080");

        // 设置是否发送 cookie 信息
        config.setAllowCredentials(true);

        // 设置允许请求的方式
        config.addAllowedMethod("*");

        // 设置允许的 header
        config.addAllowedHeader("*");

        // 2.为 url 添加映射
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3.返回定义好的 corsSource
        return new CorsFilter(corsSource);
    }

}
