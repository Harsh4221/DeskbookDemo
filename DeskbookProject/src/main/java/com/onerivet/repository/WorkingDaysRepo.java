package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.WorkingDay;

public interface WorkingDaysRepo extends JpaRepository<WorkingDay, Integer> {
	public Optional<WorkingDay> findByWorkingDayName(String days);
}
