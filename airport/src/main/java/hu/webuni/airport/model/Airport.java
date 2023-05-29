package hu.webuni.airport.model;

import java.util.Set;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Cacheable
public class Airport {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include()
	private long id;

	@Size(min = 3, max = 20)
	private String name;
	private String iata;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;
	
	@OneToMany(mappedBy = "takeoff"/*, fetch = FetchType.EAGER*/)
//	@Fetch(FetchMode.SUBSELECT)
	private Set<Flight> departures;

	@OneToMany(mappedBy = "landing")
	private Set<Flight> arrivals;
	
	public Airport(String name, String iata) {
		this.name = name;
		this.iata = iata;
	}
}
