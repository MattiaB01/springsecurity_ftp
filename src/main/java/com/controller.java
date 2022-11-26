package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

	@GetMapping("/delete") public String delete()
	{
		return "This is the delete request";
	}

	@GetMapping("/page") public String main()
	{
		return "Main Page";
	}


}
