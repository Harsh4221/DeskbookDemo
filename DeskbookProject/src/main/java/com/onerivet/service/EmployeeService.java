package com.onerivet.service;

import java.util.List;

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

public interface EmployeeService {
	
	public List<EmployeeDto> getAllEmployees();
	
	public ProfileViewDto getEmpById(String id) throws Exception;
	
	public EmployeeDto deleteEmpById(String id);
	
	public ProfileViewDto updateEmpById(String id, UpdateDto newEmployeeDto) throws Exception;
	
	public List<DesignationDto> getAllDesignations();
	
	public List<ModeOfWorkDto> getAllModeOfWorks();
	
	public List<CityDto> getAllCities();
	
	public List<FloorDto> getAllFloors(int city);
	
	public List<ColumnClassDto> getAllColumns(int floor);
	
	public List<SeatNumberDto> getAllSeatNumbers(int column);
	
	public List<WorkingDayDto> getAllWorkingDays();
}
