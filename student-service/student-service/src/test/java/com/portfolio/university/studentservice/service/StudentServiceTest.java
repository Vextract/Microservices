package com.portfolio.university.studentservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.portfolio.university.studentservice.VO.Account;
import com.portfolio.university.studentservice.VO.Course;
import com.portfolio.university.studentservice.entity.Student;
import com.portfolio.university.studentservice.repository.StudentRepository;

class StudentServiceTest {

	@Autowired
	private StudentService underTest;
	
	@MockBean
	private StudentRepository studentRepository;
	
	@MockBean
	private RestTemplate restTemplate;
	
	@BeforeEach
	void setUp() {
		Student student = 
				new Student(15L, 
						"John Doe", 
						"1, Whatever Str, Tel Aviv", 
						"some@mock.com", 
						99L);
		Course math = new Course(1L, "Math", 99.99d, "SomeDate");
		Course physics = new Course(2L, "Physics", 129.99d, "SomeDate");
		Account account = 
				new Account(99L, List.of(math,physics));
		
		Mockito.when(studentRepository.findByStudentId(15L))
		.thenReturn(student);
		Mockito.when(restTemplate.)
	}

	public void 
		
}
