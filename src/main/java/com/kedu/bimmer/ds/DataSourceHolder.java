package com.kedu.bimmer.ds;

import java.util.concurrent.Callable;

/**
 * @author liuzifeng
 */
public class DataSourceHolder {

	private static final ThreadLocal<String> holder = new ThreadLocal<>();

	public static String getDataSource() {
		return holder.get();
	}

	public static void putDataSource(String dataSource) {
		holder.set(dataSource);
	}

	public static void removeDataSource(String dataSource) {
		holder.remove();
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
