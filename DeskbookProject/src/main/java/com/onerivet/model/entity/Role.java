package com.onerivet.model.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[Role]", schema = "[Ref]")
public class Role {
	@Id
	@Column(name = "RoleId")
	private int roleId;

	@Column(name = "RoleName")
	private String roleName;
	
	@OneToMany(mappedBy = "role")
	private Set<EmployeeRoles> roles;
	
	
	

}
