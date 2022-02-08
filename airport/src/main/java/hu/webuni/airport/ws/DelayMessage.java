package hu.webuni.airport.ws;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DelayMessage {

	private int delayInMinutes;
	private OffsetDateTime timestamp;
}
