package hu.webuni.airport.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import hu.webuni.airport.model.Flight;
import hu.webuni.airport.model.QFlight;

public interface FlightRepository extends JpaRepository<Flight, Long>, 
		JpaSpecificationExecutor<Flight>,
		QuerydslPredicateExecutor<Flight>, 
		QuerydslBinderCustomizer<QFlight> {

	@Override
	default void customize(QuerydslBindings bindings, QFlight flight) {

		bindings.bind(flight.flightNumber).first((path, value) -> path.startsWithIgnoreCase(value));
		bindings.bind(flight.takeoff.iata).first((path, value) -> path.startsWith(value));
		bindings.bind(flight.takeoffTime).first((path, value) -> {
			LocalDateTime startOfDay = LocalDateTime.of(value.toLocalDate(), LocalTime.MIDNIGHT);
			return path.between(startOfDay, startOfDay.plusDays(1));
		});
	}

}
