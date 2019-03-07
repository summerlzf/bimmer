package com.kedu.bimmer.config;

import com.github.pagehelper.PageHelper;
import com.kedu.bimmer.interceptor.AccessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuzifeng on 2018/11/5.
 */
public class BimmerConfiguration {

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfig();
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(30);
	}

	@Bean
	public AccessInterceptor accessInterceptor() {
		return new AccessInterceptor();
	}

	@Bean
	public PageHelper pageHelper() {
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
		PageHelper helper = new PageHelper();
		helper.setProperties(properties);
		return helper;
	}
}
