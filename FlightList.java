
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class FlightList {
	
	private PassengerList allPassengers;
	//instantiate a TreeSet with Flight
	TreeSet<Flight> flightList = new TreeSet<Flight>();
	
	public FlightList(PassengerList passengerList) {
		allPassengers = passengerList;
	}
	
	public void add(Flight f) {
		flightList.add(f);
	}
	
	public void readFile (String filename) {
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
					System.out.println( filename + " not found " + System.getProperty("user.dir"));
					System.exit(0);
		  }
	  }
	
	private void processLine(String line, String type) {
		try {
			String parts [] = line.split(",");
			String flightCode = parts[0];
			String distination = parts[1];
			String carrier = parts[2];
			int maxPCap = Integer.parseInt(parts[3]);
			double maxWeight = Double.valueOf(parts[4]);
			double maxVolume = Double.valueOf(parts[5]);
			
			Flight f = new Flight(flightCode, distination, carrier, maxPCap, maxWeight, maxVolume);
			this.add(f);
				  
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
	
	public Flight findByFlightCode (String fcode) {
		for (Flight f: flightList) {
			if(fcode.equals(f.getFlightCode()))
			{return f;}
		}	
	return null;
	}
	
	
	
	public String getFlightDetails(String fcode) {
		String result= "";
		for (Flight f : flightList) {
			if(fcode.equals(f.getFlightCode())) {
				
			result += "Flight: "+ f.getFlightCode() + "\n";
			result += "Distination:" + f.getDestination() + "\n"; 
			result += "Checked in: " + String.format("%-5s", allPassengers.countCheckedInByFlight(f.getFlightCode()));
			result += " out of total " + String.format("%-5s", f.getPassCap()) + "\n";
			double percentage = allPassengers.getVolumetByFlight(f.getFlightCode()) / f.getBagVolCap() * 100;
			result += "Hold (volume) is " + String.format("%-8s", percentage) + "% full";	
			}
		}
		return result;
	}

}
