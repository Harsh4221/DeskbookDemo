package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.ColumnClass;
import com.onerivet.model.entity.Floor;

public interface ColumnClassRepo extends JpaRepository<ColumnClass, Integer> {
	public List<ColumnClass> findByFloor(Floor floor);
}
