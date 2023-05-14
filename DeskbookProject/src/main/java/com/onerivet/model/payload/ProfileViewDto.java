package com.onerivet.model.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileViewDto {
	
	private String profilePictureFileName;
	
	private String profilePicture;
	
	private String emailId;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private DesignationDto designation;
	
	private ModeOfWorkDto modeOfWork;
	
	private String project;
	
	private CityDto city;
	
	private FloorDto floor;
	
	private ColumnClassDto column;
	
	private SeatNumberDto seat;
	
	private Set<WorkingDayDto> days;
}
