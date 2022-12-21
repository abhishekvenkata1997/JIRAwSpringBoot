package com.me.controller;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class HelloController {

	
	@GetMapping("/hello.htm")
	public String handleGet()
	{
		return "hello-view";
	}
}
