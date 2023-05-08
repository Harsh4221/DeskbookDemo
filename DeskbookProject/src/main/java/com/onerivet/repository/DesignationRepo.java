package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	public Optional<Designation> findByDesignationName(String name);
}
