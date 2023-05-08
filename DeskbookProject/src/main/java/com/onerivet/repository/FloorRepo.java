package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.Floor;

public interface FloorRepo extends JpaRepository<Floor, Integer> {
	public Optional<Floor> findByFloorName(String floorName);
}
