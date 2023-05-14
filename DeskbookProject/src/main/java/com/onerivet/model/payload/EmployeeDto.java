package com.onerivet.model.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	private String employeeId;

	private String userId;

	private String emailId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String project;

	// private String password;

	private ModeOfWorkDto modeOfWork;

	private DesignationDto designation;

//	private SeatConfigurationDto seatConfiguration;

	private Set<EmployeeRolesDto> roles;

	private Set<EmployeeWorkingDaysDto> workingDays;
	
	private boolean active;
}
