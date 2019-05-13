package com.kedu.bimmer.ds;

import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author liuzifeng
 */
public class DataSourceHolder {

	private static final ThreadLocal<DataSourceContainer> holder = ThreadLocal.withInitial(DataSourceContainer::new);

	private static String defaultDataSource;

	static String getDataSource() {
		String ds = Optional.ofNullable(holder.get().getDataSource()).orElse(defaultDataSource);
		System.err.println("=============> 当前使用的数据源：" + ds);
		return ds;
	}

	static void putDataSource(String dataSource) {
		holder.get().addDataSource(dataSource);
	}

	static void removeDataSource(String dataSource) {
		holder.get().removeDataSource(dataSource);
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
