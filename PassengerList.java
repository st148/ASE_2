import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PassengerList {
	
	static HashSet<Passenger> passengerList = new HashSet<Passenger>();
	
	public void add(Passenger p) { 
		passengerList.add(p);	
	}
	
	public void readFile (String filename){
		  try {
			  char compType = filename.charAt(0);
			  File f = new File(filename);
			  Scanner scanner = new Scanner(f);
			  while (scanner.hasNextLine()) {
				  String inputLine = scanner.nextLine();
				  if (inputLine.length() != 0) { 
					  processLine(inputLine, String.valueOf(compType));
				  }					 
			  }
			  scanner.close();
		 }
				/**
				 * if the file is not found, stop with system exit
				 */					

		   catch (FileNotFoundException fnf){
					System.out.println( filename + " not found ");
					System.exit(0);
		  }
	  }
	
	private void processLine(String line, String type) {
		try {
			String parts [] = line.split(",");
			int bookingReference = Integer.parseInt(parts[0]);
			Name name = new Name(parts[1]);
			String flightCode = parts[2];
			Boolean checkedIn = Boolean.parseBoolean(parts[3]);
			
			Passenger p = new Passenger(bookingReference, name, flightCode, checkedIn, 0,0,0);
			this.add(p);
				  
		}
			catch (ArrayIndexOutOfBoundsException air) {
				String error = "Not enough items in  : '" + line
				                        + "' index position : " + air.getMessage();
				System.out.println(error);
			}
			catch (NumberFormatException nfe) {
				String error = "Number conversion error in '" + line + "'  - " 
				                  + nfe.getMessage();
				System.out.println(error);
			}
		}
	
	public Passenger findPassenger (String bRef, String lName) {
		for (Passenger p: passengerList) {
			String cNumS = Integer.toString(p.getBookingReference());
			if (bRef.equals(cNumS) && lName.equals(p.getPassengerName().getLastName())) {
					return p;
				}
			}
		return null;
	}
	
	public void removePassenger(String bRefName) {
		String bRef = (String) bRefName.subSequence(0, 4);
		String lName = (String) bRefName.substring(5);
		for (Passenger p: passengerList) {
			String cNumS = Integer.toString(p.getBookingReference());
			if (bRef.equals(cNumS) && lName.equals(p.getPassengerName().getLastName())) {
				passengerList.remove(p);
				}
			}
	}
	
	public int getPassengerListSize() {
		return passengerList.size();
	}
		
	public static HashSet<Passenger> getPassengerList(){
		return passengerList;
	}
	
	//May move to different class.
	public int countCheckedInByFlight(String fcode) {
		int i=0;
		for (Passenger p: passengerList) {
			if(fcode.equals(p.getFlightCode()) && p.getCheckedIn() == true) {
				i++;
			}
		}
		return i;
	}
	
	public double getWeightByFlight(String fcode) {
		double i=0;
		for (Passenger p: passengerList ) {
			if(fcode.contentEquals(p.getFlightCode())) {
				i=i+p.getBagWeight();
			}
		}
		return i;
	}
	
	public double getVolumetByFlight(String fcode) {
		double i=0;
		for (Passenger p: passengerList ) {
			if(fcode.contentEquals(p.getFlightCode())) {
				i=i+p.getBagVolume();
			}
		}
		return i;
	}
	
	public double getFeesByFlight(String fcode) {
		double i=0;
		for (Passenger p: passengerList ) {
			if(fcode.contentEquals(p.getFlightCode())) {
				i=i+p.getFee();
			}
		}
		return i;
	}

}
