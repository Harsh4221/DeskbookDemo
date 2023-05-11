package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.City;
import com.onerivet.model.entity.Floor;

public interface FloorRepo extends JpaRepository<Floor, Integer> {
	public List<Floor> findByCity(City city);
}
