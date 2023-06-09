package com.onerivet.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.ColumnClass;
import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.SeatConfiguration;
import com.onerivet.model.entity.SeatNumber;

public interface SeatConfigurationRepo extends JpaRepository<SeatConfiguration, Integer> {
	
	public SeatConfiguration findByEmployee(Employee employee);
	
	@Query(value = "SELECT s.seatNumber FROM SeatConfiguration s WHERE s.seatNumber IN (:seats)")
	public List<SeatNumber> findSeats(List<SeatNumber> seats);
	
	public SeatConfiguration findBySeatNumber(SeatNumber seat);
	
	@Query(value = "SELECT s.column Column FROM SeatConfiguration sc INNER JOIN sc.seatNumber s INNER JOIN s.column c INNER JOIN c.floor f INNER JOIN f.city ct WHERE sc.seatNumber=:seat")
	public Map<String, ColumnClass> findColumnFloorCityBySeat(SeatNumber seat);
}
