package hu.webuni.airport.model;

import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Audited
public class Image {

	@Id
	@GeneratedValue
	private long id;
	private String fileName;
	
//	@Lob
//	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] data;
	
}
