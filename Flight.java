
public class Flight {
	
	//instant variables
	private String flightCode;				//Flight Code
	private String destination;				//Destination of Flight
	private String carrier;					//Flight's Carrier Name
	private int passCap;					//Maximum Capacity of Passengers in each flight
	private double bagWeightCap;			//Maximum Capacity of Passengers' baggage weight
	private double bagVolCap;				//Maximum Capacity of Passengers' baggage volume
	private double bagsWeight;				//Total weight of checked-in baggage in each flight
	private double bagsVol;					//Total volume of checked-in baggage in each flight
	private boolean xCap;					//Default as false, become true if there is exceed capacity in flight
	
	//Constructor
	public Flight(String fc, String d, String c, int pCap, double bWCap, double bVCap, double bsW, double bsV, boolean xc) 
	{
		flightCode = fc;
		destination = d;
		carrier = c;
		passCap = pCap;
		bagWeightCap = bWCap;
		bagVolCap = bVCap;
		bagsWeight = bsW;
		bagsVol = bsV;
		xCap = xc;	
	}
	//used for TreeSets
	public int compareTo(Flight other) {
		String thisFlight = flightCode;
		return thisFlight.compareTo(other.getFlightCode());
	}
	
	
	//Need to Clarify
	/*
	//used for HashSets
	public boolean equals(Object other) {
		if (other instanceof Flight) {
			Flight otherFlight = (Flight) other;
			if (flightCode.equals(otherFlight.flightCode))
				return true;
		}
		return false;
	}

	public int hashCode() {return Flight.hashCode();}
	*/
	
	//Get Value
	public String getFlightCode() { return flightCode;}
	public String getDestination() {return destination;}
	public String getCarrier() {return carrier;}
	public int getPassCap() {return passCap;}
	public double getBagWeightCap() {return bagWeightCap;}
	public double getBagVolCap() {return bagVolCap;}
	
/*	Don't know if it is needed.
 * //Set Value
	public void setBagsWeight(double newBagsWeight) {  bagsWeight = newBagsWeight;	}
	public void setBagsVol(double newBagsVol) { bagsVol = newBagsVol; }  */	

	//****Not sure if the below methods should be put in FlightList or Flight.
	
	//Calculate total number of checked-in passenger
	public int getTotalPassenger(String fcode) {
		int total = 0;
		if (fcode.contentEquals(this.getFlightCode())) {
			for (Passenger p : PassengerList) {
				total += 1;
			}
		}
		return total;
	}
	
	//Calculate total Weight of checked-in baggage
	public double getBagsWeight(String fcode) {
		bagsWeight = 0;
		if (fcode.contentEquals(flightCode)){
			for (Passenger p : PassengerList) {
				bagsWeight += p.getBagWeight();				
			}
		}
		return bagsWeight;
	}

	//Calculate total Volume of checked-in baggage
	public double getBagsVol(String fcode) {
		bagsVol = 0;
		if (fcode.contentEquals(flightCode)){
			for (Passenger p : PassengerList) {
				bagsVol += p.getBagVol();				
			}
		}
		return bagsVol;
	}
	
	//Check Exceed Capacity
	public boolean checkExceedCapcity(String fcode) {
		//see if there is exceed capacity in each flight
		//return True if exceed capacity exists
		xCap = false;
		double bv = this.getBagsVol(fcode);
		double bw = this.getBagsWeight(fcode);
		double bvcap = this.getBagVolCap();
		double bwcap = this.getBagWeightCap();
		if ((bv < bvcap)|(bw < bwcap)) {
			xCap = true;
			return xCap;}
		//return False if no exceed capacity
		return xCap;
	}

	

}
