

public class Manager {
	
	private PassengerList allPassengers;
	private FlightList allFlights;
	private Airport airport;
	private Log logwrite;
	private GuiFrame gui;

	
	public Manager() {
		allPassengers = new PassengerList();
		allFlights = new FlightList(allPassengers);
		airport = new Airport();
		logwrite = new Log();
		gui = new GuiFrame(airport, allFlights);

	}
    
	public void run() {
		logwrite.start();
		allFlights.readFile("Flight_new.csv");
		allPassengers.readFile("Passenger_new.csv");
		airport.useloger(logwrite);
		airport.PassengerListToQueue();		
		airport.returnLoger().closeLog();
		gui.setVisible(true);
	}
}