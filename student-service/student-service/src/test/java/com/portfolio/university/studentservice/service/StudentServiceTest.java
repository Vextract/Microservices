package com.portfolio.university.studentservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.portfolio.university.studentservice.VO.Account;
import com.portfolio.university.studentservice.VO.Course;
import com.portfolio.university.studentservice.VO.FinancialResponseTemplate;
import com.portfolio.university.studentservice.entity.Student;
import com.portfolio.university.studentservice.repository.StudentRepository;

@SpringBootTest
class StudentServiceTest {

	@Autowired
	private StudentService underTest;
	
	@MockBean
	private StudentRepository studentRepository;
		
	@MockBean
	private RestTemplate restTemplate;
	
	@BeforeEach
	void setUp() {
		//For assignAccountNumberToStudent()
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
		
		Mockito.when(restTemplate.getForObject("http://FINANCIAL-DEPARTMENT/financial/accounts/" +
						student.getAccountNumber(), Account.class))
		.thenReturn(account);
		
		//For saveUser()
		Student student2 = new Student();
		student2.setStudentId(10L);
		
		Mockito.when(studentRepository.findByStudentId(10L))
		.thenReturn(student2);
		
		//For throwable in assignAccountNumberToStudent()
		Mockito.when(studentRepository.findByStudentId(20L)).thenThrow(new NullPointerException());
	}
	
	@Test
	public void whenValidStudent_thenShouldSaveProperly() {
		
		Student student = 
				new Student(15L, 
						"John Doe", 
						"1, Whatever Str, Tel Aviv", 
						"some@mock.com", 
						99L);
		
		underTest.saveUser(student);
		ArgumentCaptor<Student> studentArgumentCaptor = 
				ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(studentArgumentCaptor.capture());
		Student capturedStudent = studentArgumentCaptor.getValue();
		
		assertThat(capturedStudent).isEqualTo(student);
	}

	@Test
	public void whenValidId_thenStudentShouldBeFoundWithAccount() {
		
		Long id = 15L;
		String studentName = "John Doe";
		String courseName = "Math";
		FinancialResponseTemplate template = underTest.getStudentWithAccount(id);
		Student studentFound = template.getStudent();
		Course courseFound = template.getAccount().getCoursesAttended().get(0);
		
		verify(studentRepository).findByStudentId(id);
		verify(restTemplate).getForObject("http://FINANCIAL-DEPARTMENT/financial/accounts/" +
						studentFound.getAccountNumber(), Account.class);
		
		assertThat(studentFound.getName()).isEqualTo(studentName);
		assertThat(courseFound.getCourseName()).isEqualTo(courseName);
	}
	
	@Test
	public void whenValidIdAndNumber_ThenShouldAssignAccountToStudent() {
		Student student = new Student();
		student.setStudentId(10L);
		Account account = new Account();
		account.setAccountNumber(15L);
		
		underTest.assignAccountNumberToStudent(student.getStudentId(), account.getAccountNumber());
		
		verify(studentRepository).findByStudentId(student.getStudentId());
		student.setAccountNumber(account.getAccountNumber());
		verify(studentRepository).save(student);
		
		assertEquals(student.getAccountNumber(), account.getAccountNumber());
		assertThat(account.getAccountNumber()).isEqualTo(student.getAccountNumber());
	}
	
	@Test
	public void whenNotValidId_ThenShouldReturnMessage() {
		Account account = new Account();
		
		account.setAccountNumber(15L);
		
		assertEquals(underTest.assignAccountNumberToStudent(20L, 15L), "Something went wrong. Please try again later.");
	}
		
}
