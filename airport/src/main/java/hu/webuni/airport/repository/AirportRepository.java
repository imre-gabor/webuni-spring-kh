package hu.webuni.airport.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.airport.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long>{
	
	Long countByIata(String iata);
	
	Long countByIataAndIdNot(String iata, long id);
	
	
	@EntityGraph(attributePaths = {"departures", "address"}, type = EntityGraphType.LOAD)
	@Query("SELECT a FROM Airport a")
//	@Query("SELECT a FROM Airport a LEFT JOIN FETCH a.address")
	List<Airport> findAllWithAddressAndDepartures();

	@EntityGraph(attributePaths = {"address"}, type = EntityGraphType.LOAD)
	@Query("SELECT a FROM Airport a")
	List<Airport> findAllWithAddress(Pageable pageable);
	
	@Query("SELECT a FROM Airport a LEFT JOIN FETCH a.departures d WHERE a.id IN :ids")
	List<Airport> findByIdWithDepartures(List<Long> ids);
	
	@Query("SELECT a FROM Airport a LEFT JOIN FETCH a.arrivals ar WHERE a.id IN :ids")
	List<Airport> findByIdWithArrivals(List<Long> ids);

	@EntityGraph(attributePaths = {"arrivals"}, type = EntityGraphType.FETCH)
	@Query("SELECT a FROM Airport a")
	List<Airport> findAllWithArrivals();
}
