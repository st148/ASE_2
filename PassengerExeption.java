
public class PassengerExeption extends Exception {

	public PassengerExeption(Passenger p){
		super("Passenger already on flight: " + 
	p.getFlightCode() + ", " + p.getPassengerName());
	}
}

