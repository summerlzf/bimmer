package com.kedu.bimmer.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * Created by liuzifeng on 2018/11/5.
 */
public class BimmerConfiguration {

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