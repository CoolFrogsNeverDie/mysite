package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
	
	
	//---------main 페이지로
	
	@RequestMapping(value = "/main" , method = RequestMethod.GET)
	public String main() { 
		
		System.out.println("main controller start!");
		
		return "main/main";
	}


	
}
