package com.youyisi.vote.infrastructure.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.youyisi.vote.infrastructure.annotation.ThreadSafe;

/**
 * @author shuye
 * 
 */
@ThreadSafe
public class ExecutorsSingleton {

	private volatile ExecutorService executorService;
	private int threadNum;
	
	public synchronized ExecutorService getInstance(){
		if(null == executorService){
			synchronized (ExecutorsSingleton.class) {
				executorService = Executors.newFixedThreadPool(threadNum);
			}
		}
		return executorService;
	}
	
	public int getThreadNum(){
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
}
