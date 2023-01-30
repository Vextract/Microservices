package com.portfolio.university.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.university.financial.entity.Account;
import com.portfolio.university.financial.entity.Course;
import com.portfolio.university.financial.service.FinancialService;

@RestController
@RequestMapping("/financial")
public class FinancialController {

	@Autowired
	private FinancialService financialService;
	
	@PostMapping("/courses/")
	public Course createCourse(@RequestBody Course course) {
		return financialService.createCourse(course);
	}
	
	@PostMapping("/accounts/")
	public Account registerAccount(@RequestBody Account account) {
		return financialService.registerAccount(account);
	}
	
	@PostMapping("/accounts/{number}/add/{id}")
	public String addCourseToAccount(@PathVariable("number") Long accountNumber, @PathVariable("id") Long courseId) {
		return financialService.addCourseToAccount(accountNumber, courseId);
	}
	
	@GetMapping("/accounts/{id}")
	public Account findAccountByNumber(@PathVariable("id") Long number) {
		return financialService.findAccountByNumber(number);
	}
	
	@GetMapping("/courses/{id}")
	public Course findCourseByCourseId(@PathVariable("id") Long courseId) {
		return financialService.findCourseByCourseId(courseId);
	}
}
