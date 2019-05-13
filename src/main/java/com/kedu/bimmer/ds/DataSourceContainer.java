package com.kedu.bimmer.ds;

import java.util.LinkedList;

/**
 * @author liuzifeng
 */
class DataSourceContainer {

	private LinkedList<String> list = new LinkedList<>();

	public String getDataSource() {
		return list.isEmpty() ? null : list.getLast();
	}

	public void addDataSource(String dataSource) {
		if(!list.contains(dataSource)) {
			list.add(dataSource);
		}
	}

	public void removeDataSource(String dataSource) {
		list.remove(dataSource);
	}
}
