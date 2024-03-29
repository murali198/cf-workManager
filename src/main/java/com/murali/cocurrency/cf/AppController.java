package com.murali.cocurrency.cf;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	@Autowired
	private EventExecutor executor;

	@RequestMapping(value = "cf/", method = RequestMethod.GET)
	public String printHello(ModelMap model) throws InterruptedException, ExecutionException {
		List<String> result = executor.executeCfTask();
		model.addAttribute("msg", result);
		return "AppPage";
	}

	@RequestMapping(value = "stream/", method = RequestMethod.GET)
	public String callStream(ModelMap model) throws InterruptedException, ExecutionException {
		List<String> result = executor.executeStreamTask();
		model.addAttribute("msg", result);
		return "AppPage";
	}
}