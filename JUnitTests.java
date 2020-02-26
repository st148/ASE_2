import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.*;

public class JUnitTests {
	PassengerList pList = new PassengerList();
	
	@Before
	public void setUp() {
		
		Boolean ci1 = Boolean.parseBoolean("TRUE");
		int bref1 = 44850;
		Name name1 = new Name("Millie Villages");
		String fc1 = "EI3259";
		Passenger p1 = new Passenger(bref1, name1, fc1, ci1, 0, 0.10, 5);
		pList.add(p1);

		Boolean ci2 = Boolean.parseBoolean("FALSE");
		int bref2 = 84629;
		Name name2 = new Name("Alison Brown");
		String fc2 = "GH6494";
		Passenger p2 = new Passenger(bref2, name2, fc2, ci2, 0,0,0);
		pList.add(p2);
	}

//	@After
//	public void tearDown() {
//		PassengerList passengerList = new PassengerList();
//	}
	
	@Test		//Test Passenger name can be found and converted.
	public void testGetPassengerName() {
		Boolean tci = Boolean.parseBoolean("FALSE");
		int tbref = 12345;
		Name tname = new Name("Jimmy McBobby");
		String tfc = "AB1234";
		Passenger tp = new Passenger(tbref, tname, tfc, tci, 0,0,0);
		String expected1 = "Jimmy";
		String message1 = "Get name method failed";
		Name name1 = tp.getPassengerName();
		String actual1 = name1.getFirstName();		
		assertEquals(message1, expected1, actual1);
	}
	
	@Test		//Test if Passenger search works. (name method already tested)
	public void testFindPassenger() {
		 String tbref1 = "84629";
		 String tname1 = "Brown";
		// Passenger expected1 = new Passenger(84629, new Name("Alison Brown"),
		//		 "GH6494", Boolean.parseBoolean("FALSE"), 0,0,0);
		 String expected1 = "Alison";
		 String message1 = "Find existing Passenger method failed";		 
		 Passenger tp1 = pList.findPassenger(tbref1, tname1);		 
		 String actual1 = tp1.getPassengerName().getFirstName();
		 assertEquals(message1, expected1, actual1);
		 
		 String tbref2 = "00000";
		 String tname2 = "IncorrectName";
		 String message2 = "Finding fake Passenger method failed";		 
		 Passenger actual2 =  pList.findPassenger(tbref2, tname2);	
		 assertEquals(message2, null, actual2);
	}	
	

} //System.out.println(pList);
