package com.triercityhack19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TrierCityHack19Application {

    public static void main(String[] args) {
        SpringApplication.run(TrierCityHack19Application.class, args);
    }

    /*
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("10.143.108.62:3000");
            }
        };
    }
     */
}
