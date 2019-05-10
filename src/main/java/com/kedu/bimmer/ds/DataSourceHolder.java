package com.kedu.bimmer.ds;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author liuzifeng
 */
public class DataSourceHolder {

	private static final ThreadLocal<String> holder = new ThreadLocal<>();

	private static String defaultDataSource;

	public static String getDataSource() {
		String ds = Optional.ofNullable(holder.get()).orElse(defaultDataSource);
		System.err.println("=============> 当前使用的数据源：" + ds);
		return ds;
	}

	public static void putDataSource(String dataSource) {
		holder.set(dataSource);
	}

	public static void removeDataSource(String dataSource) {
		holder.remove();
	}

	public static String getDefaultDataSource() {
		return defaultDataSource;
	}

	public static void setDefaultDataSource(String defaultDataSource) {
		DataSourceHolder.defaultDataSource = defaultDataSource;
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
}
