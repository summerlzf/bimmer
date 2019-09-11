package com.kedu.bimmer;

import org.junit.Test;

/**
 * @author liuzifeng
 */
public class NumberTest {

    @Test
    public void integerEquals() {
        // 如下的直接赋值，相当于调用了 Integer.valueOf(int i) 方法
        Integer a = 100, b = 100, c = 200, d = 200;
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(c == 200);
    }
}
