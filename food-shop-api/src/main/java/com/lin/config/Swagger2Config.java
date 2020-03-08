package com.lin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置类
 * 访问地址：http://localhost:8088/swagger-ui.html
 * @author lkmc2
 * @date 2020/3/8 15:43
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 创建 rest 接口文档
     * @return rest 接口文档
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 定义 api 文档汇总信息
                .apiInfo(apiInfo())
                .select()
                // 扫描接口的包路径
                .apis(RequestHandlerSelectors.basePackage("com.lin.controller"))
                // 扫描所有 Controller
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建接口信息
     * @return 接口信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("电商平台接口api")
                // 联系人信息
                .contact(new Contact("lkmc2", "https://github.com/lkmc2", "lkmc2@163.com"))
                // 描述
                .description("电商平台接口api")
                // 版本
                .version("1.0.1")
                // 网站地址
                .termsOfServiceUrl("https://github.com/lkmc2")
                .build();
    }

}
