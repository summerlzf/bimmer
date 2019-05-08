package com.kedu.bimmer.ds;

/**
 * @author liuzifeng
 */
public class DataSourceHolder {

	private static final ThreadLocal<String> holder = new ThreadLocal<>();

	public static String getDataSource() {
		return "";
	}

	public static void putDataSource(String dataSource) {
		//
	}

	public static void removeDataSource(String dataSource) {
		//
	}
}
