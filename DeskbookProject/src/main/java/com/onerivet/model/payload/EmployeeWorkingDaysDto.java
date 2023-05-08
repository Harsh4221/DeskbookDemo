package com.onerivet.model.payload;

import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.WorkingDay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWorkingDaysDto {
	private int id;

	private Employee employee;

	private WorkingDay day;

	private Employee createdBy;
}
