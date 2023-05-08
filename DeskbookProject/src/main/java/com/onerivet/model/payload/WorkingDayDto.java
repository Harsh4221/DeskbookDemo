package com.onerivet.model.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkingDayDto {
	private int workingDayId;

	private String workingDayName;

	public WorkingDayDto(String workingDayName) {
		super();
		this.workingDayName = workingDayName;
	}
}
