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
	private TaskExecutor executor;

	public List<String> executeCfTask() {
		List<Task> tasks = IntStream.range(0, 10).mapToObj(i -> new Task(1, i)).collect(Collectors.toList());
		return useCompletableFutureWithExecutor(tasks);
	}

	public List<String> executeStreamTask() {
		List<Task> tasks = IntStream.range(0, 10).mapToObj(i -> new Task(1, i)).collect(Collectors.toList());
		return useParallelStream(tasks);
	}

	public List<String> useParallelStream(List<Task> tasks) {
		return tasks.parallelStream().map(Task::calculate).collect(Collectors.toList());
	}

	public List<String> useCompletableFutureWithExecutor(List<Task> tasks) {
		List<CompletableFuture<String>> futures =
				tasks.stream()
						.map(t -> CompletableFuture.supplyAsync(() -> t.calculate(), executor))
						.collect(Collectors.toList());

		List<String> result =
				futures.stream()
						.map(CompletableFuture::join)
						.collect(Collectors.toList());
		return result;
	}
}