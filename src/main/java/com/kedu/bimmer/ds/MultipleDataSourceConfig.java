package com.kedu.bimmer.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liuzifeng
 */
@Configuration
public class MultipleDataSourceConfig implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Bean
	public DynamicDataSource dataSource(MultipleDataSource multipleDataSource) {
		DynamicDataSource dataSource = new DynamicDataSource();
		Map<Object, Object> dsMap = new HashMap<>();
		for(String ds : multipleDataSource.getDataSources()) {
			DruidDataSource dds = applicationContext.getBean(ds, DruidDataSource.class);
			// 设置默认数据源
			if (ds.equalsIgnoreCase(multipleDataSource.getDefaultDataSource())) {
				dataSource.setDefaultTargetDataSource(dds);
			}
			dsMap.put(ds, dds);
		}
		dataSource.setTargetDataSources(dsMap);
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dynamicDataSource);

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		factoryBean.setMapperLocations(resolver.getResources("classpath*:mappers/*.xml"));
		factoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis-config.xml"));
		Properties properties = new Properties();
		properties.put("dialect", "mysql");
		factoryBean.setConfigurationProperties(properties);

		return factoryBean.getObject();
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		MultipleDataSource multipleDataSource = applicationContext.getBean(MultipleDataSource.class);
		String defaultDataSource = multipleDataSource.getDefaultDataSource();

		for(String ds : multipleDataSource.getDataSources()) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);
			if(ds.equalsIgnoreCase(defaultDataSource)) {
				builder.getBeanDefinition().setPrimary(true);
			}
			registry.registerBeanDefinition(ds, builder.getBeanDefinition());
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
