package com.onerivet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.SeatConfiguration;
import com.onerivet.model.entity.SeatNumber;

public interface SeatConfigurationRepo extends JpaRepository<SeatConfiguration, Integer> {
	
	public SeatConfiguration findByEmployee(Employee employee);
	
	@Query(value = "SELECT s.seatNumber FROM SeatConfiguration s WHERE s.seatNumber IN (:seats)")
	public List<SeatNumber> findSeats(List<SeatNumber> seats);
	
	public SeatConfiguration findBySeatNumber(SeatNumber seat);
}
