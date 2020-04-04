public class Manager {
	
	private PassengerList allPassengers;
	private FlightList allFlights;
	private Airport airportQueue;
	
	public Manager() {
		allPassengers = new PassengerList();
		allFlights = new FlightList(allPassengers);
		airportQueue = new Airport();
	}
    
	public void run() {
		allFlights.readFile("Flight_mock.csv");
		allPassengers.readFile("Passenger_mock.csv");
		airportQueue.PassengerListToQueue();	
	}
}
