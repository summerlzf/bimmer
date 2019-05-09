package com.kedu.bimmer.ds;

import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liuzifeng
 */
public class MultipleDataSource implements InitializingBean {

	private Set<String> dataSources;

	private String defaultDataSource;

	public MultipleDataSource(String... dataSource) {
		if (dataSource == null || dataSource.length == 0) {
			throw new RuntimeException("数据源参数为空");
		}
		dataSources = new HashSet<>(CommonUtil.asList(dataSource));
		defaultDataSource = dataSource[0];
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (dataSources == null) {
			dataSources = new HashSet<>();
		}
	}

	public Set<String> getDataSources() {
		return dataSources;
	}

	public String getDefaultDataSource() {
		return defaultDataSource;
	}
}
