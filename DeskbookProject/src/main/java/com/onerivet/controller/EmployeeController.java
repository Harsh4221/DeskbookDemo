package com.onerivet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.model.payload.CityDto;
import com.onerivet.model.payload.ColumnClassDto;
import com.onerivet.model.payload.DesignationDto;
import com.onerivet.model.payload.EmployeeDto;
import com.onerivet.model.payload.FloorDto;
import com.onerivet.model.payload.ModeOfWorkDto;
import com.onerivet.model.payload.ProfileViewDto;
import com.onerivet.model.payload.SeatNumberDto;
import com.onerivet.model.payload.UpdateDto;
import com.onerivet.model.payload.WorkingDayDto;
import com.onerivet.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public String test() {
		return "Test";
	}


	@GetMapping("/")
	public List<EmployeeDto> getAllStduent() {
		return this.employeeService.getAllEmployees();
	}

	
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {
			@Content(mediaType = "application/json", 
					schema = @Schema(implementation = ProfileViewDto.class))
			}), 
			@ApiResponse(responseCode = "404", description = "NOT FOUND", 
			content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(value = "{\"data\" : null,"
							+ "\"error\" : \"Resource Not Found\"}")
					)),
			@ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {
					@Content
					}), 
			@ApiResponse(responseCode = "403", description = "FORBIDDEN", content = {
					@Content
					}) 
			})
	@GetMapping("/{id}")
	public ProfileViewDto getById(@PathVariable("id") String id) throws Exception {
		return this.employeeService.getEmpById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeDto> deleteById(@PathVariable("id") String id) {
		EmployeeDto employeeDto = this.employeeService.deleteEmpById(id);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}

	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {
			@Content(mediaType = "application/json", 
					schema = @Schema(implementation = ProfileViewDto.class))
			}), 
			@ApiResponse(responseCode = "404", description = "NOT FOUND", 
			content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(value = "{\"data\" : null,"
							+ "\"error\" : \"Resource Not Found\"}")
					)),
			@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", 
			content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(value = "{\"data\" : null,"
							+ "\"error\" : \"Internal Server Error\"}")
					)),
			@ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {
					@Content
					}), 
			@ApiResponse(responseCode = "403", description = "FORBIDDEN", content = {
					@Content
					}) 
			})
	@PutMapping("/{id}")
	public ResponseEntity<ProfileViewDto> updateById(@PathVariable("id") String id,
			@RequestBody @Valid UpdateDto newEmployee) throws Exception {
		ProfileViewDto profileViewDto = this.employeeService.updateEmpById(id, newEmployee);
		return new ResponseEntity<ProfileViewDto>(profileViewDto, HttpStatus.OK);
	}

	@GetMapping("/designations")
	public List<DesignationDto> getDesignations() {
		return this.employeeService.getAllDesignations();
	}

	@GetMapping("/cities")
	public List<CityDto> getCities() {
		return this.employeeService.getAllCities();
	}

	@GetMapping("/mode-of-works")
	public List<ModeOfWorkDto> getModeOfWorks() {
		return this.employeeService.getAllModeOfWorks();
	}

	@GetMapping("/floors/{cityId}")
	public List<FloorDto> getFloors(@PathVariable @Positive(message = "CityId must be positive") Integer cityId) {
		return this.employeeService.getAllFloors(cityId);
	}

	@GetMapping("/columns/{floorId}")
	public List<ColumnClassDto> getColumns(
			@PathVariable @Positive(message = "FloorId must be positive") Integer floorId) {
		return this.employeeService.getAllColumns(floorId);
	}

	@GetMapping("/seats/{columnId}")
	public List<SeatNumberDto> getSeatNumbers(
			@PathVariable @Positive(message = "ColumnId must be positive") Integer columnId) {
		return this.employeeService.getAllSeatNumbers(columnId);
	}

	@GetMapping("/working-days")
	public List<WorkingDayDto> getWorkinfDays() {
		return this.employeeService.getAllWorkingDays();
	}
}
