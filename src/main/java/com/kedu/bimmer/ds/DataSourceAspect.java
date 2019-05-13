package com.kedu.bimmer.ds;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author liuzifeng
 */
@Component
@Aspect
public class DataSourceAspect {

	@Around("@annotation(ds)")
	public Object proceed(ProceedingJoinPoint pjp, DataSource ds) throws Throwable {
		DataSourceHolder.putDataSource(ds.value());
		try {
			return pjp.proceed();
		} finally {
			DataSourceHolder.removeDataSource(ds.value());
		}
	}
}
