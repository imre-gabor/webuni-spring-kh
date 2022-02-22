package hu.webuni.airport.xmlws;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;

	@Autowired
	private AirportXmlWs airportXmlWs;

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, airportXmlWs);
		endpoint.publish("/airport");
		return endpoint;
	}
}
