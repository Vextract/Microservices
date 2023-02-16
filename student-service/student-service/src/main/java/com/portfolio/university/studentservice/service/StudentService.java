package com.portfolio.university.studentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.portfolio.university.studentservice.VO.Account;
import com.portfolio.university.studentservice.VO.FinancialResponseTemplate;
import com.portfolio.university.studentservice.entity.Student;
import com.portfolio.university.studentservice.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private RestTemplate restTemplate;

	public Student saveUser(Student student) {
		return studentRepository.save(student);
	}

	public FinancialResponseTemplate getStudentWithAccount(Long studentId) {
		FinancialResponseTemplate response = new FinancialResponseTemplate();
		Student student = studentRepository.findByStudentId(studentId);
		Account account = restTemplate
				.getForObject("http://FINANCIAL-DEPARTMENT/financial/accounts/" +
						student.getAccountNumber(), Account.class);
		response.setStudent(student);
		response.setAccount(account);
		return response;
	}

	public String assignAccountNumberToStudent(Long studentId, Long accountNumber) {
		try {
			Student student = studentRepository.findByStudentId(studentId);
			student.setAccountNumber(accountNumber);
			studentRepository.save(student);
			return "Assigned account number " + accountNumber + " to ID " + studentId;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return "Something went wrong";
		}
	}

	
}
