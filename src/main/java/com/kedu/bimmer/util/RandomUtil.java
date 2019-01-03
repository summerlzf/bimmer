package com.kedu.bimmer.util;

import java.util.Random;

/**
 * Created by liuzifeng on 2019/1/4.
 */
public class RandomUtil {

	private static final String[] names = {"Jacky", "Nancy", "Paul", "David", "Vincent", "Lucy", "Kate", "Tony", "Cindy", "Jason",
			"Cat", "Mia", "Sophia", "Isabella", "Mason", "Lucas", "Zoe", "Christal", "Julia", "Oliver"};

	/**
	 * 返回随机的英文名
	 * @return
	 */
	public static String getUserName() {
		int i = new Random().nextInt(names.length);
		return names[i];
	}
}
