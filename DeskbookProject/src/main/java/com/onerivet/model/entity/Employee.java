package com.onerivet.model.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "[Employee]", schema = "[dbo]")
public class Employee {
	@Id
	@Column(name = "EmployeeId")
	private int employeeId;

	@Column(name = "UserId")
	private String userId;

	@Column(name = "EmailId")
	private String emailId;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "ProjectName")
	private String project;


	@OneToOne
	@JoinColumn(name = "ModeOfWorkId")
	private ModeOfWork modeOfWork;

	@OneToOne
	@JoinColumn(name = "DesignationId")
	private Designation designation;

//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
//	@JoinColumn(name = "SeatConfigurationId")
//	private SeatConfiguration seatConfiguration;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
//	private Set<EmployeeRoles> roles;

	//@JsonManagedReference
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
//	private Set<EmployeeWorkingDays> workingDays;
	
	@OneToOne
	@JoinColumn(name = "ModifiedBy")
	private Employee modifiedBy;
	
	@Column(name = "IsActive")
	private boolean active;
}
