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
public class UpdateDto {
	private int employeeId;

	private String userId;

	private String emailId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String project;

	private int modeOfWork;

	private int designation;

	private SeatConfigurationUpdateDto seatConfiguration;

	private int[] roles;

	private int[] workingDays;
}
