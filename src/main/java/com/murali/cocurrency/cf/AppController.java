package com.murali.cocurrency.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printHello(ModelMap model) throws InterruptedException, ExecutionException {
		EventExecutor executor = (EventExecutor)SpringApplicationContext.getBean("eventExecutor");	
		CompletableFuture<String> result = executor.executeTask();             				   
		model.addAttribute("msg",result.get() );
		return "AppPage";
	}
}