package com.portfolio.university.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.university.financial.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	Course findCourseByCourseId(Long courseId);

}
