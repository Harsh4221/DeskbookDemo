package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.ColumnClass;

public interface ColumnClassRepo extends JpaRepository<ColumnClass, Integer> {
	public Optional<ColumnClass> findByColumnName(String columnName);
}
