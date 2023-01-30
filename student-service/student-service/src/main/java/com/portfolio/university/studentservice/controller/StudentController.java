package com.portfolio.university.studentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.university.studentservice.VO.FinancialResponseTemplate;
import com.portfolio.university.studentservice.entity.Student;
import com.portfolio.university.studentservice.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/")
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveUser(student);
	}
	
	@PostMapping("/{id}assignto/{number}")
	public String assignAccountNumberToStudent(@PathVariable("id") Long studentId, 
												@PathVariable("number") Long accountNumber) {
		return studentService.assignAccountNumberToStudent(studentId, accountNumber);
	}
	
	@GetMapping("/{id}") 
	public FinancialResponseTemplate getStudentWithFinancialInfo(@PathVariable("id") Long studentId) {
		return studentService.getStudentWithAccount(studentId);
	}
}
