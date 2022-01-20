package hu.webuni.airport.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.airport.dto.AirportDto;
import hu.webuni.airport.dto.FlightDto;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;

@Mapper(componentModel = "spring")
public interface AirportMapper {

	List<AirportDto> airportsToDtos(List<Airport> airports);

	AirportDto airportToDto(Airport airport);

	@IterableMapping(qualifiedByName = "base")
	List<AirportDto> airportsToBaseDtos(List<Airport> airports);

	@Named("base")
	@Mapping(ignore = true, target = "address")
	@Mapping(ignore = true, target = "departures")
	@Mapping(ignore = true, target = "arrivals")
	AirportDto airportToBaseDto(Airport airport);

	Airport dtoToAirport(AirportDto airportDto);
	
	@Mapping(ignore = true, target = "takeoff")
	@Mapping(ignore = true, target = "landing")
	FlightDto flightToDto(Flight flight);
}
