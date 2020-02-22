public class Manager {
	
	private PassengerList allPassengers;
	private FlightList allFlights;
	public Manager() {
		allFlights = new FlightList();
		allPassengers = new PassengerList();
	}
    
	public void run() {
		
		allFlights.readFile("Flight_mock.csv");
		allPassengers.readFile("Passenger_mock.csv");
		
		System.out.println(allPassengers.countCheckedInByFlight("AM008"));
		
		
    /*	CheckInGUI gui = new CheckInGUI(allPassengers, allFlights);
        gui.setVisible(true);

     */
	}
}
