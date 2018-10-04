package com.kedu.bimmer;

import com.kedu.bimmer.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BimmerApplicationTest {

	@Test
	public void contextLoads() {
		String s = "123456";
		System.out.println("MD5:  " + CommonUtil.MD5(s));
		System.out.println("SHA1: " + CommonUtil.SHA1(s));
		System.out.println("SHA1: " + CommonUtil.hash(s));
		System.out.println(CommonUtil.isLegalPassword(""));
	}

}
