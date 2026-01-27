package com.springboot.springboot2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("物业管理项目物业系统")   // 标题
                        .description("进行物业管理") // 描述
                        .version("1.0")                  // 版本
                        .contact(new Contact()
                                .name("成都工业职业技术学院")
                                .url("http://localhost:8081/")
                                .email("absolutezero.cold200@simplelogin.com")));
    }
}
