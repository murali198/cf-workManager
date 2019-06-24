package com.murali.cocurrency.cf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EventExecutor {

	@Autowired
	@Qualifier("cfTaskExecutor")
	private TaskExecutor cfExecutor;

	public CompletableFuture<List> executeTask() {
		return CompletableFuture.supplyAsync(()-> {
			System.out.println("CompletableFuture Supplier Thread name[" + Thread.currentThread().getName()+"]");
			return IntStream.range(1, 3).mapToObj( i -> getData(i)).collect(Collectors.toList());
		}, cfExecutor);
	}

	private String getData(int val)  {
		String name = Thread.currentThread().getName();
		System.out.println("Thread name[" + name+"] for the val ["+ val +"]");
		return name;
	}
}