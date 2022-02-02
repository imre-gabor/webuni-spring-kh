package hu.webuni.airport.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.airport.api.model.AirportDto;
import hu.webuni.airport.api.model.FlightDto;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;

@Mapper(componentModel = "spring")
public interface FlightMapper {

	Flight dtoToFlight(FlightDto flightDto);

	FlightDto flightToDto(Flight flight);
	List<FlightDto> flightsToDtos(List<Flight> flights);

	List<FlightDto> flightsToDtos(Iterable<Flight> findAll);

	@Mapping(ignore = true, target = "address")
	@Mapping(ignore = true, target = "departures")
	@Mapping(ignore = true, target = "arrivals")
	AirportDto airportToDto(Airport airport);
}
