import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FlightList {
	
	//instantiate a TreeSet with Flight
	TreeSet<Flight> flightList = new TreeSet<Flight>();
	
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
					System.out.println( filename + " not found ");
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
	
	public String getFlightReport() {
			String report = "";
				for (Flight f : flightList) {
					report += "Flight Code: " + f.getFlightCode() + "\n";
					report += "Destination: " + f.getDestination() + "\n";
					report += "Carrier: " + f.getCarrier() + "\n";
					report += "No. of passengers checked-in: " 
								+ String.format("%-4d", f.getTotalPassenger(f.getFlightCode())) + "\n";
					report += "Total Weight of baggage: "
								+ String.format("%-7.2d", f.getBagsWeight(f.getFlightCode())) + "\n";
					report += "Total Volume of baggage: "
								+ String.format("%-7.2d", f.getBagsVol(f.getFlightCode())) + "\n";
					//report += "Total Excess fee collected: "
					//			+ String.format("%-7.2d", ??? + "\n");
					report += "\n";
				}
				return report;
	}
	
		
}
	