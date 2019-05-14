package com.kedu.bimmer.ds;

import com.alibaba.druid.pool.DruidDataSource;
import com.kedu.bimmer.util.CommonUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liuzifeng
 */
@EnableTransactionManagement
@Configuration
public class MultipleDataSourceConfig implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, EnvironmentAware {

	private ApplicationContext applicationContext;

	private Environment environment;

	@Bean
	public DynamicDataSource dataSource(MultipleDataSource multipleDataSource) {
		// 将 Environment 环境对象置入 DataSourceHolder 中
		DataSourceHolder.setEnvironment(environment);
		// 创建数据源
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setLenientFallback(false);
		Map<Object, Object> dsMap = new HashMap<>();
		for(String ds : multipleDataSource.getDataSources()) {
			DruidDataSource dds = applicationContext.getBean(ds, DruidDataSource.class);
			if(!initDataSource(ds, dds)) {
				continue;
			}
			// 设置默认数据源
			if (ds.equalsIgnoreCase(multipleDataSource.getDefaultDataSource())) {
				dataSource.setDefaultTargetDataSource(dds);
			}
			dsMap.put(ds, dds);
		}
		dataSource.setTargetDataSources(dsMap);
		return dataSource;
	}

	private boolean initDataSource(String ds, DruidDataSource dataSource) {
		String url = environment.getProperty(ds + ".url"), username = environment.getProperty(ds + ".username");
		if (CommonUtil.isBlank(url) || CommonUtil.isBlank(username)) {
			return false;
		}
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(username);
//		String pwd = environment.getProperty(ds + ".password");
		dataSource.setPassword("admin123456");

		// 设置默认参数
		if(dataSource.getMaxActive() == 8 || dataSource.getMaxActive() == 5) {
			dataSource.setMaxActive(100);
		}
		if(dataSource.getInitialSize() == 0 || dataSource.getInitialSize() == 1) {
			dataSource.setInitialSize(10);
		}
		if(dataSource.getMinIdle() == 0) {
			dataSource.setMinIdle(10);
		}
		if(!dataSource.isPoolPreparedStatements()) {
			dataSource.setMaxPoolPreparedStatementPerConnectionSize(5);
		}
		// 设置获取连接的最大等待时间为5s
		if(dataSource.getMaxWait() < 0 || dataSource.getMaxWait() > 5000L) {
			dataSource.setMaxWait(5000L);
		}
		if(dataSource.getValidationQuery() == null) {
			dataSource.setValidationQuery("SELECT 'x'");
		}
		if(dataSource.getValidationQueryTimeout() < 0) {
			dataSource.setValidationQueryTimeout(0);
		}
		return true;
	}

	@ConditionalOnMissingBean
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

//		Properties prop = new Properties();
//		prop.setProperty("offsetAsPageNum", "true");
//		prop.setProperty("rowBoundsWithCount", "true");
//		prop.setProperty("reasonable", "true");
//		prop.setProperty("helperDialect", "mysql"); // 配置mysql数据库的方言
//		PageInterceptor interceptor = new PageInterceptor();
//		interceptor.setProperties(prop);
//		factoryBean.setPlugins(new Interceptor[] {interceptor});

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

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
