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
		Boolean ci = Boolean.parseBoolean("FALSE");

		int bref1 = 44850;
		Name name1 = new Name("Millie Villages");
		String fc1 = "EI3259";
		Passenger p1 = new Passenger(bref1, name1, fc1, ci, 0,0,0);
		pList.add(p1);

		int bref2 = 84629;
		Name name2 = new Name("Alison Brown");
		String fc2 = "GH6494";
		Passenger p2 = new Passenger(bref2, name2, fc2, ci, 0,0,0);
		pList.add(p2);
	}

//	@After
//	public void tearDown() {
//		PassengerList passengerList = new PassengerList();
//	}
	
	@Test
	public void testFindPassenger() {
		 String tbref1 = "84629";
		 String tname1 = "Brown";
		 String expected1 = "Alison";
		 String message1 = "Find Passenger method failed";		 
		 Passenger tp = pList.findPassenger(tbref1, tname1);		 
		 String actual1 = tp.getPassengerName().getFirstName();
		 assertEquals(message1, expected1, actual1);
	}	
	
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

} //System.out.println(pList);
