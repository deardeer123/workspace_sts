package com.green.basicBoard.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 생성한 인터셉터 클래스의 적용 시점 정의
// WebMvcConfigurer 인터페이스 구현
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getPrintInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .addPathPatterns("/web/**")

                .addPathPatterns("/bookAdmin/**")
                .excludePathPatterns("/test3");




        registry.addInterceptor(getDBInterceptor())
                .order(1)
                .addPathPatterns("/test1")
                //.addPathPatterns("/test2")
                .addPathPatterns("/test3");
    }

    @Bean
    public  PrintInterceptor getPrintInterceptor(){
        return new PrintInterceptor();
    }

    @Bean
    public DBInterceptor getDBInterceptor(){
        return new DBInterceptor();
    }
}
