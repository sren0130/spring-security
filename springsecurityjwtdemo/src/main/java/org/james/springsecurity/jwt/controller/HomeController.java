package org.james.springsecurity.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/home")
	public String homePage() {
		return "<h1>User home page</h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1>Administration time</h1>";
	}

}
