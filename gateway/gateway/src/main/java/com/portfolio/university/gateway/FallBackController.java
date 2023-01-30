package com.portfolio.university.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	@GetMapping("/studentServiceFallBack")
	public String studentServiceFallBack() {
		return "Something went wrong. Please try again later";
	}
	
	@GetMapping("/financialDepartmentFallBack")
	public String financialDepartmentFallBack() {
		return "Something went wrong. Please try again later";
	}
}
