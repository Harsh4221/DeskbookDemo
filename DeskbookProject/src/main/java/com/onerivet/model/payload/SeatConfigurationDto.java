package com.onerivet.model.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatConfigurationDto {
	private int seatConfigurationId;

	private CityDto city;

	private FloorDto floor;

	private ColumnClassDto columnClass;

	private SeatNumberDto seatNumber;
}
