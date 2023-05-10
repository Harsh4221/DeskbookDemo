package com.onerivet.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.EmployeeWorkingDays;

public interface EmployeeWorkingDaysRepo extends JpaRepository<EmployeeWorkingDays, Integer> {
	
	public List<EmployeeWorkingDays> findByEmployee(Employee employee);
	
	//@Query(value = "DELETE FROM EmployeeWorkingDays e WHERE e.employee=:employee")
	public void deleteByEmployee(Employee employee);
	
//	@Modifying
//	@Query(value = "UPDATE EmployeeWorkingDays e SET e.deletedBy=:value, e.deletedDate=:time WHERE e.employee=:employee")
//	public void updateDeletedFields(int value, LocalDateTime time, Employee employee);
	
}
