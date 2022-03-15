package hu.webuni.flights.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hu.webuni.flights.dto.Airline;

public interface FlightsApi {

    @GetMapping("/flight/{from}/{to}")
    List<Airline> searchFlight(@PathVariable("from") String from, @PathVariable("to") String to);

}