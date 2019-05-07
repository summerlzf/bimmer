package com.kedu.bimmer.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Jef
 */
@Configuration
public class DruidConfig {

	@Value("${db.ds.read.common.url}")
	private String url;
	@Value("${db.ds.read.common.username}")
	private String username;
	@Value("${db.ds.read.common.password:admin123456}")
	private String password;

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
		return ds;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid/*");
        bean.addInitParameter("resetEnable", "true");
        return bean;
    }

}
