package com.kedu.bimmer.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author liuzifeng
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDataSource();
	}
}
