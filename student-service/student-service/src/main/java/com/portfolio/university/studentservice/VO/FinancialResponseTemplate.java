package com.portfolio.university.studentservice.VO;

import java.util.List;

import com.portfolio.university.studentservice.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialResponseTemplate {
	
	private Student student;
	private Account account;
}
