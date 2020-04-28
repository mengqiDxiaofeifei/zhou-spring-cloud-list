package com.zhou.redis.test;

import redis.clients.jedis.Jedis;

//定义线程
public class MyTask implements Runnable {

	private String lockKey = "lockKey";
	private int taskNum;

	public MyTask(int taskNum) {
		this.setTaskNum(taskNum);
	}

	public void run() {
		Jedis jedis = new Jedis("localhost");
		LockCase lock = new LockCase(jedis, lockKey);
		lock.lock();

		// 模拟业务执行15秒
		lock.sleepBySecond(15);

		lock.unlock();

	}

	public int getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}

}
