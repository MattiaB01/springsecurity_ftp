package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controllerPages {

	@GetMapping("/")
	public String main()
	{
		return "main";
	}
	

	@PostMapping("/index")
	public String index2()
	{
		return "index";
	}
	
	
	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	@GetMapping("/notLogin")
	public String notLog()
	{
		return "index";
	}
}
