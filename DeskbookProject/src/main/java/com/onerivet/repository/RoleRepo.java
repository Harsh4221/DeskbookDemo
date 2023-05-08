package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	public Optional<Role> findByRoleName(String roleName);
}
