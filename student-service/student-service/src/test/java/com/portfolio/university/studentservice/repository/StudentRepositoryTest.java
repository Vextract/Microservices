package com.portfolio.university.studentservice.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.university.studentservice.entity.Student;

@DataJpaTest
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp() {
		Student student = new Student();
		student.setName("Jane Doe");
		student.setHomeAddress("5, Smth Str., Beer Sheva");
		student.setEmail("any@mock.com");
		student.setAccountNumber(55L);
		
		entityManager.persist(student);
	}

	@Test
	public void whenFindByStudentId_thenReturnStudent() {
		Student student = studentRepository.findByStudentId(1L);
		
		assertEquals("Jane Doe", student.getName());
	}

}
