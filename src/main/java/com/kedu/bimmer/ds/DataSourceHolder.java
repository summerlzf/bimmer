package com.kedu.bimmer.ds;

import com.kedu.bimmer.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author liuzifeng
 */
public class DataSourceHolder {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceHolder.class);

	private static final ThreadLocal<DataSourceContainer> holder = ThreadLocal.withInitial(DataSourceContainer::new);

	private static String defaultDataSource;

	private static Environment environment;

	private static boolean isValidDataSource(String dataSource) {
		String url = environment.getProperty(dataSource + ".url"), username = environment.getProperty(dataSource + ".username");
		return !CommonUtil.isBlank(url) && !CommonUtil.isBlank(username);
	}

	static String getDataSource() {
		String ds = Optional.ofNullable(holder.get().getDataSource()).orElse(defaultDataSource);
		logger.info("=============> 当前使用的数据源：" + ds);
		return ds;
	}

	static void putDataSource(String dataSource) {
		if (!isValidDataSource(dataSource)) {
			throw new RuntimeException("DataSource [" + dataSource + "] is not valid");
		}
		holder.get().addDataSource(dataSource);
	}

	static void removeDataSource(String dataSource) {
		holder.get().removeDataSource(dataSource);
	}

	public static void setDefaultDataSource(String defaultDataSource) {
		DataSourceHolder.defaultDataSource = defaultDataSource;
	}

	public static void setEnvironment(Environment environment) {
		DataSourceHolder.environment = environment;
	}

	public static <T> T callInDataSource(String dataSource, Callable<T> callable) {
		try {
			putDataSource(dataSource);
			return callable.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			removeDataSource(dataSource);
		}
	}

	public static void runInDataSource(String dataSource, Runnable runnable) {
		try {
			putDataSource(dataSource);
			runnable.run();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			removeDataSource(dataSource);
		}
	}
}
