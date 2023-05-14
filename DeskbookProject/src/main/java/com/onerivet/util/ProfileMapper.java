package com.onerivet.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onerivet.model.entity.Employee;
import com.onerivet.model.entity.EmployeeWorkingDays;
import com.onerivet.model.entity.SeatConfiguration;
import com.onerivet.model.entity.WorkingDay;
import com.onerivet.model.payload.CityDto;
import com.onerivet.model.payload.ColumnClassDto;
import com.onerivet.model.payload.DesignationDto;
import com.onerivet.model.payload.FloorDto;
import com.onerivet.model.payload.ModeOfWorkDto;
import com.onerivet.model.payload.ProfileViewDto;
import com.onerivet.model.payload.SeatNumberDto;
import com.onerivet.model.payload.WorkingDayDto;
import com.onerivet.repository.EmployeeWorkingDaysRepo;

@Component
public class ProfileMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ImageUtils imageUtils;
	
	@Autowired
	private EmployeeWorkingDaysRepo employeeWorkingDaysRepo;
	
	public ProfileViewDto getProfile(Employee employee, SeatConfiguration seatConfiguration) throws Exception {
		ProfileViewDto profile = new ProfileViewDto();
		Set<WorkingDayDto> days = new LinkedHashSet<WorkingDayDto>();
		
		
		if(employee.getProfilePictureFileName() != null && employee.getProfilePictureFilePath() != null) {
			profile.setProfilePicture(imageUtils.encodeImage(employee.getProfilePictureFilePath()));
			profile.setProfilePictureFileName(employee.getProfilePictureFileName());
		}

		profile.setEmailId(employee.getEmailId());
		profile.setFirstName(employee.getFirstName());
		profile.setLastName(employee.getLastName());
		profile.setPhoneNumber(employee.getPhoneNumber());
		
		if(employee.getDesignation()!=null)
			profile.setDesignation(this.modelMapper.map(employee.getDesignation(), DesignationDto.class));
		
		profile.setProject(employee.getProject());
		
		if(seatConfiguration!=null) {
			profile.setCity(this.modelMapper.map(seatConfiguration.getSeatNumber().getColumn().getFloor().getCity(), CityDto.class));
			profile.setColumn(this.modelMapper.map(seatConfiguration.getSeatNumber().getColumn(), ColumnClassDto.class));
			profile.setFloor(this.modelMapper.map(seatConfiguration.getSeatNumber().getColumn().getFloor(), FloorDto.class));
			profile.setSeat(this.modelMapper.map(seatConfiguration.getSeatNumber(), SeatNumberDto.class));
			profile.getSeat().setBooked(true);
		
		}
		

		if(employee.getModeOfWork()!=null) {
			
			profile.setModeOfWork(this.modelMapper.map(employee.getModeOfWork(), ModeOfWorkDto.class));
			
			if(employee.getModeOfWork().getModeOfWorkId() == 1) {
				List<EmployeeWorkingDays> workingDays = this.employeeWorkingDaysRepo.findByEmployee(employee);
				for (EmployeeWorkingDays day : workingDays) {
					if(day.getDeletedBy() == null)
						days.add(this.modelMapper.map(day.getDay(), WorkingDayDto.class));
				}
			} else if(employee.getModeOfWork().getModeOfWorkId() == 3) {
					days.add(this.modelMapper.map(new WorkingDay(1, "Monday"), WorkingDayDto.class));
					days.add(this.modelMapper.map(new WorkingDay(2, "Tuesday"), WorkingDayDto.class));
					days.add(this.modelMapper.map(new WorkingDay(3, "Wednesday"), WorkingDayDto.class));
					days.add(this.modelMapper.map(new WorkingDay(4, "Thursday"), WorkingDayDto.class));
					days.add(this.modelMapper.map(new WorkingDay(5, "Friday"), WorkingDayDto.class));
			}
			
			profile.setDays(days);
		}
			
		
		
		
		return profile;
	}
}
