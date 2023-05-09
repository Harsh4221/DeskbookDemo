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
	
	@GetMapping("/get-all-designations")
	public List<DesignationDto> getDesignations(){
		return this.employeeService.getAllDesignations();
	}
	
	@GetMapping("/get-all-cities")
	public List<Integer> getCities() {
		return this.employeeService.getAllCities();
	}
	
	@GetMapping("/get-all-mode-of-works")
	public List<ModeOfWorkDto> getModeOfWorks() {
		return this.employeeService.getAllModeOfWorks();
	}
	
	@GetMapping("/get-all-floors")
	public List<FloorDto> getFloors() {
		return this.employeeService.getAllFloors();
	}
	
	@GetMapping("/get-all-columns")
	public List<ColumnClassDto> getColumns() {
		return this.employeeService.getAllColumns();
	}
	
	@GetMapping("/get-all-seats")
	public List<SeatNumberDto> getSeatNumbers() {
		return this.employeeService.getAllSeatNumbers();
	}

	@GetMapping("/get-all-working-days")
	public List<WorkingDayDto> getWorkinfDays() {
		return this.employeeService.getAllWorkingDays();
	}
}
