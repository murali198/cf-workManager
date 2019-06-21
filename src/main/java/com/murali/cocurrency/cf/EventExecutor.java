package com.murali.cocurrency.cf;

import java.util.concurrent.CompletableFuture;
import org.springframework.core.task.TaskExecutor;

public class EventExecutor {

	public CompletableFuture<String> executeTask() {
		TaskExecutor cfExecutor = (TaskExecutor) SpringApplicationContext.getBean("cfTaskExecutor");		
		return CompletableFuture.supplyAsync(()-> getData(), cfExecutor);
	}

	private String getData()  {
		return "event complete";
	}
}