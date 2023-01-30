package com.portfolio.university.financial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.university.financial.entity.Account;
import com.portfolio.university.financial.entity.Course;
import com.portfolio.university.financial.repository.AccountRepository;
import com.portfolio.university.financial.repository.CourseRepository;

@Service
public class FinancialService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CourseRepository courseRepository;

	public Account registerAccount(Account account) {
		return accountRepository.save(account);
	}

	public Account findAccountByNumber(Long number) {
		return accountRepository.findByAccountNumber(number);
	}
	
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public Course findCourseByCourseId(Long courseId) {
		return courseRepository.findCourseByCourseId(courseId);
	}

	public List<Course> findAllCoursesByStudentId(Long studentId) {
		return null;
	}

	public String addCourseToAccount(Long accountNumber, Long courseId) {
		try {
			Account account = accountRepository.findByAccountNumber(accountNumber);
			Course course = courseRepository.findCourseByCourseId(courseId);
			account.getCoursesAttended().add(course);
			accountRepository.save(account);
			return "Succesful adding of "+ course.getCourseName() 
			+ " course to Account number " + accountNumber;
		} catch (Exception e) {
			return "Something went wrong";
		}
	}
}
