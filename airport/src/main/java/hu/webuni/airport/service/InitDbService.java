package hu.webuni.airport.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.airport.model.Address;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.AddressRepository;
import hu.webuni.airport.repository.AirportRepository;
import hu.webuni.airport.repository.FlightRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InitDbService {

	private final AirportRepository airportRepository;
	private final FlightService flightService;
	private final AddressRepository addressRepository;
	private final FlightRepository flightRepository;
	
	@Transactional
	public void deleteAllData() {
		flightRepository.deleteAll();
		addressRepository.deleteAll();
		airportRepository.deleteAll();
	}
	
	@Transactional
	public void addInitData() {
		Airport airport1 = airportRepository.save(new Airport("airport1", "BUD"));
		Airport airport2 = airportRepository.save(new Airport("airport2", "LAX"));
		Airport airport3 = airportRepository.save(new Airport("airport3", "JFK"));
		Airport airport4 = airportRepository.save(new Airport("airport4", "LGW"));
		
		airport1.setAddress(addressRepository.save(new Address(0, "HU", "Budapest", "XY út 10", "1115")));
		airport2.setAddress(addressRepository.save(new Address(0, "US", "Los Angeles", "XY street", "1115")));
		airport3.setAddress(addressRepository.save(new Address(0, "US", "New York", "XY street", "1115")));
		airport4.setAddress(addressRepository.save(new Address(0, "UK", "London", "XY street", "1115")));
		
		flightService.save(new Flight(0, "ABC123", LocalDateTime.of(2022, 6, 10, 10, 10), airport1, airport2));
		flightService.save(new Flight(0, "ABC456", LocalDateTime.of(2022, 6, 10, 12, 10), airport2, airport3));
		flightService.save(new Flight(0, "DEF234", LocalDateTime.of(2022, 6, 12, 14, 10), airport2, airport4));
		flightService.save(new Flight(0, "GHI345", LocalDateTime.of(2022, 6, 13, 16, 10), airport4, airport1));
	}
}
