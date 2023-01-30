package com.portfolio.university.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.university.studentservice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByStudentId(Long studentId);
}
