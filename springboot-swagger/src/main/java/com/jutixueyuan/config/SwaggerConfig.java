package com.jutixueyuan.config;

import com.google.common.base.Predicates;
import com.jutixueyuan.annotation.IncludeSwagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 黄药师
 * @date 2021-01-26 15:48
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Configuration
public class SwaggerConfig {

    /**
     * Docket 摘要对象，通过对象配置描述文件的信息。
     *
     * @return
     */
    @Bean
    public Docket docket() {

        // apiInfo 对象 文档信息对象
        // ApiInfoBuilder 构造对象
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        // 文档的标题
        apiInfoBuilder.title("xxx管理系统接口文档");

        // Contact 设置练习方式对象
        Contact contact = new Contact("巨梯学院",
                "http://www.jutixueyuan.com",
                "jutixueyuan@163.com");

        apiInfoBuilder.contact(contact);

        // 文档描述
        apiInfoBuilder.description("用于管理系统的前后端协同开发,内部资料");
        //版本号
        apiInfoBuilder.version("1.0.1");

        // 构建一个 apiInfo 对象
        ApiInfo apiInfo = apiInfoBuilder.build();

        // select()
        // 返回ApiSelectorBuilder对象，通过对象调用build()可以创建Docket对象

        /**
         *
         *  指定规则的扫描：
         *
         *   01  RequestHandlerSelectors.basePackage("com.jutixueyuan.api") 指定的包扫描
         *
         *   02  RequestHandlerSelectors.withClassAnnotation(IncludeSwagger.class)
         *       withClassAnnotation 如果这个类上面有这个注解 会生成api文档
         *
         *   03  RequestHandlerSelectors.withMethodAnnotation(IncludeSwagger.class)
         *      withMethodAnnotation 如果这方法上有指定的注解 会生成api文档
         *
         *   04  .paths(PathSelectors.regex("/stu/.*"))
         *        regex  根据 请求的url 访问 生成指定的api文档
         *
         */
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                // 可以添加自定的扫描包
//                .apis(RequestHandlerSelectors.basePackage("com.jutixueyuan.api"))
                //   如果有  IncludeSwagger 标记的类 生成api文档
//                 .apis(RequestHandlerSelectors.withClassAnnotation(IncludeSwagger.class))
                //取反   如果有 IncludeSwagger 标记的方法 生成api文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(IncludeSwagger.class))

                //取反给条件 方法上面没有 IncludeSwagger 注解就可生成api文档

                // Predicates条件判断对象
                // not 取反  and 并且
//                .apis(Predicates.not(RequestHandlerSelectors.withMethodAnnotation(IncludeSwagger.class)))

                // 通过路径扫描指定的 url 生成 api文档
                // Predicates.or 加条件扫描多个 url
                // 扫描多个url
                // or 或者

                .paths(
                        Predicates.or(
                                PathSelectors.regex("/stu/.*"),
                                PathSelectors.regex("/emp/.*")
                        )
                )

                .build();

        return docket;
    }
}
