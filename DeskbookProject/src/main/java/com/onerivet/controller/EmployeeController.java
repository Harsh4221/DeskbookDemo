package com.onerivet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.onerivet.model.payload.SeatNumberDto;
import com.onerivet.model.payload.UpdateDto;
import com.onerivet.model.payload.WorkingDayDto;
import com.onerivet.service.EmployeeService;

@RestController
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

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable("id") int id) {
		return this.employeeService.getEmpById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeDto> deleteById(@PathVariable("id") int id) {
		EmployeeDto employeeDto = this.employeeService.deleteEmpById(id);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateById(@PathVariable("id") int id, @RequestBody UpdateDto newEmployee)
			throws Exception {
		EmployeeDto employeeDto = this.employeeService.updateEmpById(id, newEmployee);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
	}
	
	@GetMapping("/designations")
	public List<DesignationDto> getDesignations(){
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
	public List<FloorDto> getFloors(@PathVariable int cityId) {
		return this.employeeService.getAllFloors(cityId);
	}
	
	@GetMapping("/columns/{floorId}")
	public List<ColumnClassDto> getColumns(@PathVariable int floorId) {
		return this.employeeService.getAllColumns(floorId);
	}
	
	@GetMapping("/seats/{columnId}")
	public List<SeatNumberDto> getSeatNumbers(@PathVariable int columnId) {
		return this.employeeService.getAllSeatNumbers(columnId);
	}

	@GetMapping("/working-days")
	public List<WorkingDayDto> getWorkinfDays() {
		return this.employeeService.getAllWorkingDays();
	}
}
