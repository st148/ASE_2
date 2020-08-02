
import java.util.concurrent.Semaphore;

import java.util.*; 



public class Airport  {
	int numberOfDesks = 2;    
	private PassengerList passengerQueue = new PassengerList();
	private FlightList flightlist = new FlightList(passengerQueue);
	
	//this acts as a "pass" to a empty desk, meaning only one thread
	//can access a desk at a time
	private Semaphore freeDesk = new Semaphore(numberOfDesks);
	
	//this is to stop the log class from closing before the simulation 
	//has finished
	boolean lastCheckinComplete = false;
	
	//creates array of no. of desks long; auto set to 0 (waiting),
	//I was thinking we could change so 2 is closed or its named
	//after the thread it is serving.
	public String[] deskStatus = new String[numberOfDesks];
	
	private Log superlogwiter;
	
	public void useloger(Log l) {
		this.superlogwiter = l;
	}
	
	public Log returnLoger() {
		//checks if the last check-in has finished before returning log
		while(!lastCheckinComplete) {
			//if not true wait a little then try again
			try {Thread.sleep(100);} 
			catch (InterruptedException e) {e.printStackTrace();}
		} 
		return this.superlogwiter;
	}

	//This class is for the actual check-in process
	class CheckIn{
		// defining which desk is in use
		public int currantDesk = 999; //999 is now an error number
		
		//To count the total number of check-ins for testing 
		int noCheckIns = 0;
		public int getC () {return this.noCheckIns;};
		
		//For the actual check in process
		public void useDesk() {	
			Thread t = Thread.currentThread();
			
			//sleep for simulation purposes
			try {t.sleep(100);} 
			catch (InterruptedException e) {e.printStackTrace();}
			

			//increase the count of check-ins
			int n = this.noCheckIns; n++;
			this.noCheckIns = n;
			// the counting is mostly for testing purposes and can be removed
			
			//this will write a new line to the Log class
			String threadName = t.getName();
			String message = "Check-In " + noCheckIns + " (" + threadName 
					+ ") at desk " + currantDesk;                    
			superlogwiter.writeToLog(message);
			
			//remove passenger from queue
			passengerQueue.removePassenger(threadName);
			              
			//Checks if every passenger in the queue has now checked in so we 
			//can close the class.
			if(n == passengerQueue.getPassengerListSize()) {
			lastCheckinComplete = true;
			}
			

		}  
		
			
	
		//These methods are just for identifying which desk the thread will use.
		
		//This one will find the next desk which isn't in use
		public int identifyFreeDeskNo() {
			int n = 999; //error number 
			for (int i = 0; i < deskStatus.length; i++) {
				if (Integer.parseInt(deskStatus[i]) == 0) {
					n = i+1; 
					this.currantDesk = n; 
					break;
				}
			}
			return n;
		}		
		//This one will mark the desk empty after use
		public void emptyIdentifiedDesknoNo(int n) {
			deskStatus[n-1] = Integer.toString(0); 
			this.currantDesk = 999; //System.out.println("emtied desk no.:-" + n + " [" + currantDesk + "]");
		}
	}

	//Initiates the check-in class so as many threads are running, there
	//is only one check-in process being used.
	CheckIn	checkin = new CheckIn();

	//Defines a Passenger thread
	class PassengerInQueue  implements Runnable {
		public void run(){
			String NameBRef = Thread.currentThread().getName();
			System.out.println(NameBRef + " joined queue.");

			//Obtains "pass" for thread/passenger to use an empty desk
			try {freeDesk.acquire();} 
			catch (InterruptedException e) {e.printStackTrace();}
			
			//finds the desk no the thread/passenger will use
			int deskno = checkin.identifyFreeDeskNo(); 
			//thread/passenger does the check-in process			 
			checkin.useDesk();
			//thread/passenger leaves desk marking it empty
			checkin.emptyIdentifiedDesknoNo(deskno);
			//releases "pass" to be used by the next thread/passenger
			freeDesk.release();
			//can be used in testing:-
			System.out.println(NameBRef + " completed check in");
		}	
	}


	//creates running thread for every passenger in list
	public void PassengerListToQueue(){
		HashSet<Passenger> passengerList = PassengerList.getPassengerList();
		for(Passenger p: passengerList ) {
			passengerQueue.add(p);
			String plName = p.getPassengerName().getLastName();
			String pbRef = Integer.toString(p.getBookingReference());
			String NameBRef = plName + pbRef; //gives unique code 
			Thread t = new Thread(new PassengerInQueue()); 
			t.setName(NameBRef); //for simulation 
			try {t.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
			t.start();
		}
	}
	//String for showing Desk Status
	public String getDeskStatuses(int deskNo) {
		String message = "";
		try {
		if (Integer.parseInt(deskStatus[deskNo-1]) == 0) {
				message = "Desk " + deskNo + " is waiting.";	
			} else if (Integer.parseInt(deskStatus[deskNo-1]) == 2) {
				message = "Desk " + deskNo + " is closed.";
			} else {
				String bRefName = deskStatus[deskNo-1];
				String bRef = (String) bRefName.subSequence(0, 4);
				String lName = (String) bRefName.substring(5);
				Name pName = passengerQueue.findPassenger(bRef,lName).getPassengerName();
			
				message = "Desk " + deskNo + " is serving " + pName;
				message += ". " + "1 bag of " + passengerQueue.findPassenger(bRef,lName).getBagWeight();
				message +=	"kg. A baggage fee of £" + passengerQueue.findPassenger(bRef,lName).getFee() +".";
			}
		}
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + nfe.getMessage();
			System.out.println(error);
		}
		return message;
	}
		//String for showing Queue Status (all passenger not yet checked in)
		public String getQueueStatus() {
			String status = "";
			int n = passengerQueue.getPassengerListSize() - checkin.getC();
			status += "There are currently " + n + " people waiting in the queue: " ;
			int i = 0;
				for (Passenger p  : passengerQueue.getPassengerList()) {
					if (i == 0){
						status += String.format("%-10s", "Flight");
						status += String.format("%-30s", "Customer Name");
						status += String.format("%-18s", "Luggage Weight");
						status += String.format("%-10s", "Volume");
					}	
					status += String.format("");
					status += String.format("%-10", p.getFlightCode());
					status += String.format("%-30s",p.getPassengerName());
					status += String.format("%-18s", p.getBagWeight());
					status += String.format("%-10s", p.getBagVolume());
					status += "\n"; i++;
				}
			return status;	
			}
	


	
	public PassengerList getPassengerQueue() {
		return passengerQueue;
	}
	
	

	
	



}

