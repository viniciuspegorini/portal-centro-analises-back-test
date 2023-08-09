package com.portal.centro.API.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT","PATCH","OPTIONS","DELETE")
                .allowedHeaders("Authorization","x-xsrf-token",
                        "Access-Control-Allow-Headers", "Origin",
                        "Accept", "X-Requested-With", "Content-Type",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Auth-Id-Token");
    }
}
