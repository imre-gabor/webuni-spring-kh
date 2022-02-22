package hu.webuni.airport.wsclient;

public class Main {

	public static void main(String[] args) {

		new AirportXmlWsImplService()
			.getAirportXmlWsImplPort()
			.getHistoryById(462L)
			.forEach(h ->{
				System.out.format("Name:%s, Revision:%d, RevType:%s%n", h.getData().getName(), h.getRevision(), h.getRevType().toString());
			});
	}

}
