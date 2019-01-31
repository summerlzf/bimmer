package com.kedu.bimmer.threads;

/**
 * Created by liuzifeng on 2019/1/31.
 */
public class RunnableTask implements Runnable {

	private Runnable runnable;

	public RunnableTask(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void run() {
		try {
			runnable.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.notify();
		}
	}

	public void done() throws InterruptedException {
		this.wait();
	}
}
