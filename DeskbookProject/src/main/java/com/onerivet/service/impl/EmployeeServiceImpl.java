package com.onerivet.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.exception.ResourceNotFoundException;
import com.onerivet.model.entity.Designation;
import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.EmployeeWorkingDays;
import com.onerivet.model.entity.ModeOfWork;
import com.onerivet.model.entity.SeatConfiguration;
import com.onerivet.model.entity.WorkingDay;
import com.onerivet.model.payload.CityDto;
import com.onerivet.model.payload.ColumnClassDto;
import com.onerivet.model.payload.DesignationDto;
import com.onerivet.model.payload.EmployeeDto;
import com.onerivet.model.payload.EmployeeWorkingDaysDto;
import com.onerivet.model.payload.FloorDto;
import com.onerivet.model.payload.ModeOfWorkDto;
import com.onerivet.model.payload.SeatNumberDto;
import com.onerivet.model.payload.WorkingDayDto;
import com.onerivet.repository.CityRepo;
import com.onerivet.repository.ColumnClassRepo;
import com.onerivet.repository.DesignationRepo;
import com.onerivet.repository.EmployeeRepo;
import com.onerivet.repository.FloorRepo;
import com.onerivet.repository.ModeOfWorkRepo;
import com.onerivet.repository.SeatNumberRepo;
import com.onerivet.repository.WorkingDaysRepo;
import com.onerivet.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private DesignationRepo designationRepo;

	@Autowired
	private ModeOfWorkRepo modeOfWorkRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private FloorRepo floorRepo;

	@Autowired
	private ColumnClassRepo columnClassRepo;

	@Autowired
	private SeatNumberRepo seatNumberRepo;

	@Autowired
	private WorkingDaysRepo workingDaysRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return this.employeeRepo.findAll().stream().map((employee) -> this.modelMapper.map(employee, EmployeeDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmpById(int id) {
		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee With id " + id + " not found."));
		return this.modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto deleteEmpById(int id) {
		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee With id " + id + " not found."));

		this.employeeRepo.delete(employee);

		return this.modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmpById(int id, EmployeeDto newEmployeeDto) throws Exception {
		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee With id " + id + " not found."));

		employee.setFirstName(newEmployeeDto.getFirstName());
		employee.setLastName(newEmployeeDto.getLastName());
		employee.setPhoneNumber(newEmployeeDto.getPhoneNumber());
		employee.setProject(newEmployeeDto.getProject());

		employee.setDesignation(this.modelMapper.map(newEmployeeDto.getDesignation(), Designation.class));
		employee.setModeOfWork(this.modelMapper.map(newEmployeeDto.getModeOfWork(), ModeOfWork.class));
		employee.setSeatConfiguration(
				this.modelMapper.map(newEmployeeDto.getSeatConfiguration(), SeatConfiguration.class));

		employee.getSeatConfiguration().setCreatedBy(employee);
		employee.getSeatConfiguration().setEmployee(employee);

		if (newEmployeeDto.getModeOfWork().getModeOfWorkName().equals("Hybrid")) {
			employee.setWorkingDays(newEmployeeDto.getWorkingDays().stream()
					.map((workingDayDto) -> this.modelMapper.map(workingDayDto, EmployeeWorkingDays.class))
					.collect(Collectors.toSet()));
		} else if (newEmployeeDto.getModeOfWork().getModeOfWorkName().equals("Regular")
				&& newEmployeeDto.getWorkingDays().isEmpty()) {

			employee.setWorkingDays(newEmployeeDto.getWorkingDays().stream()
					.map((workingDayDto) -> this.modelMapper.map(workingDayDto, EmployeeWorkingDays.class))
					.collect(Collectors.toSet()));

		} else if (newEmployeeDto.getModeOfWork().getModeOfWorkName().equals("Work From Home")) {
			if (!newEmployeeDto.getWorkingDays().isEmpty()) {
				throw new Exception("Error ! You cannot select working days!");
			}
		}

		this.employeeRepo.save(employee);

		return this.modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public List<DesignationDto> getAllDesignations() {
		return this.designationRepo.findAll().stream()
				.map((designation) -> this.modelMapper.map(designation, DesignationDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ModeOfWorkDto> getAllModeOfWorks() {
		return this.modeOfWorkRepo.findAll().stream()
				.map((modeOfWork) -> this.modelMapper.map(modeOfWork, ModeOfWorkDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<CityDto> getAllCities() {
		return this.cityRepo.findAll().stream().map((city) -> this.modelMapper.map(city, CityDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<FloorDto> getAllFloors() {
		return this.floorRepo.findAll().stream().map((floor) -> this.modelMapper.map(floor, FloorDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ColumnClassDto> getAllColumns() {
		return this.columnClassRepo.findAll().stream()
				.map((column) -> this.modelMapper.map(column, ColumnClassDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<SeatNumberDto> getAllSeatNumbers() {
		List<SeatNumberDto> seatNumbers = this.seatNumberRepo.findAll().stream()
				.map((seatNumber) -> this.modelMapper.map(seatNumber, SeatNumberDto.class))
				.collect(Collectors.toList());

		return seatNumbers;
	}

	@Override
	public List<WorkingDayDto> getAllWorkingDays() {
		return this.workingDaysRepo.findAll().stream()
				.map((workingDay) -> this.modelMapper.map(workingDay, WorkingDayDto.class))
				.collect(Collectors.toList());
	}

}
