package com.portfolio.university.studentservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	private Long courseId;
	private String courseName;
	private Double coursePrice;
	private String dateStartingAndDateEnding;
}
