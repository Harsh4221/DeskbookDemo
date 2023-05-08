package com.onerivet.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[EmployeeRole]", schema = "[dbo]")
public class EmployeeRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeRoleId")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "RoleId")
	private Role role;
	
	@OneToOne
	@JoinColumn(name = "CreatedBy")
	private Employee createdBy;
}
