package hu.webuni.airport.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDto {

	private long id;
	
	@NotEmpty
	private String flightNumber;
	
	@NotNull
	private LocalDateTime takeoffTime;

	private AirportDto takeoff;
	
	private AirportDto landing;

}
