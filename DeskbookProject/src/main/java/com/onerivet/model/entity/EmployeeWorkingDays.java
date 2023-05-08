package com.onerivet.model.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "[EmployeeWorkingDays]", schema = "[dbo]")
public class EmployeeWorkingDays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeWorkingDayId")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "WorkingDayId")
	private WorkingDay day;
	
	@OneToOne
	@JoinColumn(name = "CreatedBy")
	private Employee createdBy;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private Set<EmployeeWorkingDays> workingDays;
}
