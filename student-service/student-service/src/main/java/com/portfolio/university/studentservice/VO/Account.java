package com.portfolio.university.studentservice.VO;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	private Long accountNumber;
	private List<Course> coursesAttended = new ArrayList<>();
}
