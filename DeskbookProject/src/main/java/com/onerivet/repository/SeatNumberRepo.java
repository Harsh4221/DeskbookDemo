package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.SeatNumber;

public interface SeatNumberRepo extends JpaRepository<SeatNumber, Integer> {
	public Optional<SeatNumber> findBySeatNumber(String seatNumber);
}
