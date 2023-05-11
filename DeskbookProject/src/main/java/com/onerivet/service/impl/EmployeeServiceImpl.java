package com.onerivet.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.exception.ResourceNotFoundException;
import com.onerivet.model.entity.ColumnClass;
import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.EmployeeWorkingDays;
import com.onerivet.model.entity.Floor;
import com.onerivet.model.entity.SeatConfiguration;
import com.onerivet.model.entity.SeatNumber;
import com.onerivet.model.payload.CityDto;
import com.onerivet.model.payload.ColumnClassDto;
import com.onerivet.model.payload.DesignationDto;
import com.onerivet.model.payload.EmployeeDto;
import com.onerivet.model.payload.FloorDto;
import com.onerivet.model.payload.ModeOfWorkDto;
import com.onerivet.model.payload.SeatNumberDto;
import com.onerivet.model.payload.UpdateDto;
import com.onerivet.model.payload.WorkingDayDto;
import com.onerivet.repository.CityRepo;
import com.onerivet.repository.ColumnClassRepo;
import com.onerivet.repository.DesignationRepo;
import com.onerivet.repository.EmployeeRepo;
import com.onerivet.repository.EmployeeWorkingDaysRepo;
import com.onerivet.repository.FloorRepo;
import com.onerivet.repository.ModeOfWorkRepo;
import com.onerivet.repository.SeatConfigurationRepo;
import com.onerivet.repository.SeatNumberRepo;
import com.onerivet.repository.WorkingDaysRepo;
import com.onerivet.service.EmployeeService;

import jakarta.transaction.Transactional;

@Transactional
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
	private SeatConfigurationRepo seatConfigurationRepo;

	@Autowired
	private EmployeeWorkingDaysRepo employeeWorkingDaysRepo;

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
	public EmployeeDto updateEmpById(int id, UpdateDto newEmployeeDto) throws Exception {
		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee With id " + id + " not found."));

		employee.setFirstName(newEmployeeDto.getFirstName());
		employee.setLastName(newEmployeeDto.getLastName());
		employee.setPhoneNumber(newEmployeeDto.getPhoneNumber());
		employee.setProject(newEmployeeDto.getProject());

		employee.setDesignation(this.designationRepo.findById(newEmployeeDto.getDesignation()).get());
		employee.setModeOfWork(this.modeOfWorkRepo.findById(newEmployeeDto.getModeOfWork()).get());

		employee.setModifiedBy(employee);
		employee.setModifiedDate(LocalDateTime.now());
		
		
		SeatNumber seatNumber = this.seatNumberRepo.findById(newEmployeeDto.getSeatConfiguration().getSeatNumber())
				.get();

		SeatConfiguration seatConfiguration = this.seatConfigurationRepo.findBySeatNumber(seatNumber);
		
		
		if (seatConfiguration == null) {
			seatConfiguration = new SeatConfiguration();
		} 
		
		if(seatConfiguration.getEmployee()!=null && seatConfiguration.getEmployee().getEmployeeId() != employee.getEmployeeId())
			throw new ResourceNotFoundException("Already Booked");
			
		seatConfiguration.setCreatedBy(employee);
		seatConfiguration.setEmployee(employee);
		seatConfiguration.setModifiedBy(employee);
		seatConfiguration.setModifiedDate(LocalDateTime.now());
		seatConfiguration.setSeatNumber(seatNumber);
		
		
		if(employee.getModeOfWork().getModeOfWorkName().equalsIgnoreCase("Hybrid")) {
			List<EmployeeWorkingDays> employeeWorkingDays = employeeWorkingDaysRepo.findByEmployee(employee);
			
			for(EmployeeWorkingDays day : employeeWorkingDays) {
				day.setDeletedBy(employee);
				day.setDeletedDate(LocalDateTime.now());
				employeeWorkingDaysRepo.save(day);
			}
			
			for(int i : newEmployeeDto.getWorkingDays()) {
				employeeWorkingDaysRepo.save(new EmployeeWorkingDays(employee, this.workingDaysRepo.findById(i).get(), employee, employee, LocalDateTime.now()));
			}	
		}
		
		Employee save = this.employeeRepo.save(employee);
		seatConfigurationRepo.save(seatConfiguration);
		return this.modelMapper.map(save, EmployeeDto.class);
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
	public List<FloorDto> getAllFloors(int city) {

		List<Floor> floors = this.floorRepo.findByCity(this.cityRepo.findById(city).get());

		return floors.stream().map((floor) -> this.modelMapper.map(floor, FloorDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ColumnClassDto> getAllColumns(int floor) {

		List<ColumnClass> columns = this.columnClassRepo.findByFloor(this.floorRepo.findById(floor).get());

		return columns.stream().map((column) -> this.modelMapper.map(column, ColumnClassDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<SeatNumberDto> getAllSeatNumbers(int column) {
		List<SeatNumber> seats = this.seatNumberRepo.findByColumn(this.columnClassRepo.findById(column).get());

		List<SeatNumber> bookedSeats = this.seatConfigurationRepo.findSeats(seats);

		for (int i = 0, j = 0; j < bookedSeats.size();) {
			if (seats.get(i).getSeatNumberId() == bookedSeats.get(j).getSeatNumberId()) {
				seats.get(i).setBooked(true);
				j++;
			}
			i++;
		}

		return seats.stream().map((seat) -> this.modelMapper.map(seat, SeatNumberDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<WorkingDayDto> getAllWorkingDays() {
		return this.workingDaysRepo.findAll().stream()
				.map((workingDay) -> this.modelMapper.map(workingDay, WorkingDayDto.class))
				.collect(Collectors.toList());
	}

}
