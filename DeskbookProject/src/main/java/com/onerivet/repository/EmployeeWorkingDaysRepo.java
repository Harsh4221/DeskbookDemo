package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.EmployeeWorkingDays;

public interface EmployeeWorkingDaysRepo extends JpaRepository<EmployeeWorkingDays, Integer> {
	
	public EmployeeWorkingDays findByEmployee(Employee employee);
	
	//@Query(value = "DELETE FROM EmployeeWorkingDays e WHERE e.employee=:employee")
	public void deleteByEmployee(Employee employee);
}
