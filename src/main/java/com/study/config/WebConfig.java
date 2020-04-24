package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author guanpeng
 * @description TODO
 * @date 2020/4/23 1:46 下午
 * @since
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("/login.html");
        registry.addViewController("/index").setViewName("/index.html");
        registry.addViewController("/toLogout").setViewName("/logout.html");
    }
}
