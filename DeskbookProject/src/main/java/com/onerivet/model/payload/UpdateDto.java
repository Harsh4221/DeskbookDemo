package com.onerivet.model.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {

	@NotEmpty(message = "Upload valid image, only .jpg, .jpeg and .png are allowed")
	@Pattern(regexp = "^[a-zA-Z0-9]+(\\.jpg|\\.png|\\.jpeg)", message = "Upload valid image, only .jpg, .jpeg and .png are allowed")
	private String imageName;
	
	
	private String image;
	
	//@NotEmpty(message = "Please enter your First Name")
	@NotBlank(message = "Please enter your First Name")
	@Size(min=2,message="First name must be of 2 characters or more")
	@Size(max=100,message="Your first name cannot be exceed 100 characters")
	@Pattern(regexp = "^[A-Za-z]+('[a-z]+)?$", message="Please enter valid First name")
	private String firstName;

	@NotEmpty(message = "Please enter your Last Name")
	@Size(min=2,message="Last name must be of 2 characters or more")
	@Size(max=100,message="Your Last name cannot be exceed 100 characters")
	@Pattern(regexp = "^[A-Za-z]+$", message="Please enter valid Last name")
	private String lastName;

	@Pattern(regexp = "^[0-9]+$",message="Please enter a numeric value only")
	@Size(min=10,max=10,message="10-digit number is required")
	@NotEmpty(message="Please enter phone no.")
	private String phoneNumber;

	
	@Size(max = 200, message = "Exceeded maximum character limit of 200")
	private String project;
	
	@NotNull(message = "Please select Mode of work")
	private Integer modeOfWork;
	
	@NotNull(message = "Please select designation")
	private Integer designation;

	@NotNull(message = "Please select city")
	private Integer city;

	@NotNull(message = "Please select floor")
	private Integer floor;

	@NotNull(message = "Please select column")
	private Integer column;

	@NotNull(message = "Please select Seat No.")
	private Integer seat;
	
	
	private int[] workingDays;
}
