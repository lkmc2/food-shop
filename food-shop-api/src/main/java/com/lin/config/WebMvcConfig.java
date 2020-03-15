package com.lin.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Web MVC 配置
 * @author lkmc2
 * @date 2020/3/15 16:24
 */
@Configuration
public class WebMvcConfig {

    /**
     * 创建 RestTemplate 对象
     * @param builder 构建器
     * @return RestTemplate 对象
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
