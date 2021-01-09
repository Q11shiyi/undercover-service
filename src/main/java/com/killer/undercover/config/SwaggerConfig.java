package com.killer.undercover.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger配置类
 *
 * @author huanghuiqiang
 * @create: 21.1.9 10:08
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean(value = "mini")
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("小程序端")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.killer.undercover.controller.mini"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("谁是卧底小程序项目 Restful API 说明文档")
                .contact(new Contact("huanghuiqiang1111@gmail.com","",""))
                .description("基于Swagger2和Knife4j实现的接口文档")
                .termsOfServiceUrl("http://localhost:8888/undercover/")
                .version("1.0")
                .build();
    }
}
