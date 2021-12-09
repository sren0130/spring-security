package org.james.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "<h1>User home page</h1>";
	}

	@GetMapping("/admin")
	public String admin() {
		return "<h1>Administration time</h1>";
	}
}
