package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	public Optional<City> findByCityName(String cityName);
}
