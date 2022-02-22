package hu.webuni.airport.wsclient;

public class Main {

	public static void main(String[] args) {

		AirportXmlWs airportXmlWsImplPort = new AirportXmlWsImplService()
			.getAirportXmlWsImplPort();
		
		airportXmlWsImplPort
			.getHistoryById(462L)
			.forEach(h ->{
				System.out.format("Name:%s, Revision:%d, RevType:%s%n", h.getData().getName(), h.getRevision(), h.getRevType().toString());
			});
		
		GetFlightDelay flightDelay = new GetFlightDelay();
		flightDelay.setArg0(123L);
		System.out.println(airportXmlWsImplPort.getFlightDelay(flightDelay));
	}

}
