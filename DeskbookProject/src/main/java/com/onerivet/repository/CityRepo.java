package com.onerivet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	public Optional<City> findByCityName(String cityName);
	
	@Query(value = "SELECT c.id FROM City c")
	public List<Integer> getAllCities();
}
