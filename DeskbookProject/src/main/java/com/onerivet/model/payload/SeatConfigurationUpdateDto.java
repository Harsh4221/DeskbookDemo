package com.onerivet.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatConfigurationUpdateDto {
	private int seatConfigurationId;

	private int city;

	private int floor;

	private int columnClass;

	private int seatNumber;
}
