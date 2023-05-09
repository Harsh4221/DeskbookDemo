package com.onerivet.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.SeatConfiguration;

public interface SeatConfigurationRepo extends JpaRepository<SeatConfiguration, Integer> {
	
	public SeatConfiguration findByEmployee(Employee employee);
}
