package com.kedu.bimmer;

/**
 * 死锁的示例代码
 *
 * Created by liuzifeng on 2019/2/21.
 */
public class DeadlockTest {

	private static final Object obj1 = new Object(), obj2 = new Object();

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			synchronized (obj1) {
				System.out.println("[t1]: get lock from obj1");
				try {
					Thread.sleep(100);
					synchronized (obj2) {
						System.out.println("obj1 -> obj2");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(() -> {
			synchronized (obj2) {
				System.out.println("[t2]: get lock from obj2");
				try {
					Thread.sleep(100);
					synchronized (obj1) {
						System.out.println("obj2 -> obj1");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
	}
}
