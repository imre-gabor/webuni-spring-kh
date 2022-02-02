package hu.webuni.airport.web;

import java.lang.reflect.Method;
import java.util.List;

import javax.validation.Valid;

import org.springframework.core.MethodParameter;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.querydsl.core.types.Predicate;

import hu.webuni.airport.api.FlightControllerApi;
import hu.webuni.airport.api.model.FlightDto;
import hu.webuni.airport.mapper.FlightMapper;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.FlightRepository;
import hu.webuni.airport.service.FlightService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FlightController implements FlightControllerApi{

	private final FlightService flightService;
	private final FlightRepository flightRepository;
	private final FlightMapper flightMapper;
	
	private final QuerydslPredicateArgumentResolver predicateResolver;
	private final NativeWebRequest request;
	
	
	@Override
	public ResponseEntity<FlightDto> createFlight(@Valid FlightDto flightDto) {
		Flight flight = flightService.save(flightMapper.dtoToFlight(flightDto));
		return ResponseEntity.ok(flightMapper.flightToDto(flight));
	}
	@Override
	public ResponseEntity<List<FlightDto>> searchFlights(@Valid FlightDto example) {
		return ResponseEntity.ok(flightMapper.flightsToDtos(flightService.findFlightsByExample(flightMapper.dtoToFlight(example))));
	}
	
	

	@Override
	public ResponseEntity<Void> startDelayPolling(Long flightId, Long rate) {
		flightService.startDelayPollingForFlight(flightId, rate);
		return ResponseEntity.ok().build();
	}
	@Override
	public ResponseEntity<Void> stopDelayPolling(Long flightId) {
		flightService.stopDelayPollingForFlight(flightId);
		return ResponseEntity.ok().build();
	}
	
	
	@Override
	public ResponseEntity<List<FlightDto>> searchFlights2(@Valid Integer id, @Valid String flightNumber,
			@Valid List<String> takeoffTime, @Valid String takeoffIata) {
		
		Predicate predicate = extractPredicate("configurePredicate");
		return ResponseEntity.ok(flightMapper.flightsToDtos(flightRepository.findAll(predicate)));
	}
	
	public void configurePredicate(@QuerydslPredicate(root = Flight.class) Predicate predicate) {}
	
	private Predicate extractPredicate(String predicateConfigurerMethodName) {
		Method method = null;
		try {
			method = this.getClass().getMethod(predicateConfigurerMethodName, Predicate.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		MethodParameter parameter = new MethodParameter(method, 0);
		ModelAndViewContainer mavContainer = null;
		WebDataBinderFactory webDataBinderFactory = null;
		
		try {
			return (Predicate) predicateResolver.resolveArgument(parameter, mavContainer, request, webDataBinderFactory);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
