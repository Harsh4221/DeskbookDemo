package com.onerivet.service;

import java.util.List;

import com.onerivet.model.payload.ColumnClassDto;
import com.onerivet.model.payload.DesignationDto;
import com.onerivet.model.payload.EmployeeDto;
import com.onerivet.model.payload.FloorDto;
import com.onerivet.model.payload.ModeOfWorkDto;
import com.onerivet.model.payload.SeatNumberDto;
import com.onerivet.model.payload.UpdateDto;
import com.onerivet.model.payload.WorkingDayDto;

public interface EmployeeService {
	
	public List<EmployeeDto> getAllEmployees();
	
	public EmployeeDto getEmpById(int id);
	
	public EmployeeDto deleteEmpById(int id);
	
	public EmployeeDto updateEmpById(int id, UpdateDto newEmployeeDto) throws Exception;
	
	public List<DesignationDto> getAllDesignations();
	
	public List<ModeOfWorkDto> getAllModeOfWorks();
	
	public List<Integer> getAllCities();
	
	public List<FloorDto> getAllFloors();
	
	public List<ColumnClassDto> getAllColumns();
	
	public List<SeatNumberDto> getAllSeatNumbers();
	
	public List<WorkingDayDto> getAllWorkingDays();
}
