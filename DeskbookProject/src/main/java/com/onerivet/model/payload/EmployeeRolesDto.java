package com.onerivet.model.payload;

import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRolesDto {
	private int id;

	private Employee employee;

	private Role role;

	private Employee createdBy;
}
