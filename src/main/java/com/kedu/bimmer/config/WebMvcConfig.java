package com.kedu.bimmer.config;

import com.kedu.bimmer.interceptor.AccessInterceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Jef
 */
@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessInterceptor());
        super.addInterceptors(registry);
    }
}