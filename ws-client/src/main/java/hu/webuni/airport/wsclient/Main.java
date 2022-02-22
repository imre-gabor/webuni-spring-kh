package hu.webuni.airport.wsclient;

import java.util.concurrent.ExecutionException;

public class Main {

	public static void main(String[] args) throws Exception {

		AirportXmlWs airportXmlWsImplPort = new AirportXmlWsImplService()
			.getAirportXmlWsImplPort();
		
		airportXmlWsImplPort
			.getHistoryById(462L)
			.forEach(h ->{
				System.out.format("Name:%s, Revision:%d, RevType:%s%n", h.getData().getName(), h.getRevision(), h.getRevType().toString());
			});

		
		
		GetFlightDelay flightDelay = new GetFlightDelay();
		flightDelay.setArg0(123L);
		
		
//		System.out.println(airportXmlWsImplPort.getFlightDelay(flightDelay));
		airportXmlWsImplPort.getFlightDelayAsync(flightDelay, res -> {
			try {
				System.out.println(res.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
	
		for(int i=0;i<6; i++) {
			System.out.println("Client doing something else...");
			Thread.sleep(1000);
		}
	}

}
