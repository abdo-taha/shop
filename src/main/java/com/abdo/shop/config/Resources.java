package com.abdo.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Resources implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // System.out.println(System.getProperty("user.dir"));
        String localFilePath = "file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";

        registry.addResourceHandler("/data/**")
                .addResourceLocations(localFilePath);
    }
}