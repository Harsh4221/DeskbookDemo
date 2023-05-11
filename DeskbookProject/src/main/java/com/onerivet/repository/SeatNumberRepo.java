package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.ColumnClass;
import com.onerivet.model.entity.SeatNumber;

public interface SeatNumberRepo extends JpaRepository<SeatNumber, Integer> {
	public List<SeatNumber> findByColumn(ColumnClass column);
}
