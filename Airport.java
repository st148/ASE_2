import java.util.TreeSet;
import java.util.concurrent.Semaphore;

public class Airport {
	int numberOfDesks = 2;    
	private Semaphore Desk = new Semaphore(numberOfDesks);

	class CheckIn{
		//count the total number of check ins
		int noCheckIns = 0;
		public int getC () {return this.noCheckIns;};
		public void useDesk() {		
			// Include any check in code required for GUI.

			
			//increase the count of checkins
			int n = this.noCheckIns; n++;
			this.noCheckIns = n;		// the counting is mostly for testing purposes rn
			String threadName = Thread.currentThread().getName();
			System.out.println("Check-In " +noCheckIns + " (" + threadName + ") in progress.");
		}
	}

	CheckIn	checkin = new CheckIn();

	//Define a Passenger thread
	class PassengerInQueue  implements Runnable {
		public void run(){
			String NameBRef = Thread.currentThread().getName();
			System.out.println(NameBRef + " joined queue.");	
			try { 
				Desk.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			checkin.useDesk();
			Desk.release();
			System.out.println(NameBRef + " completed check in");
		}	
	}

	//creates running thread for every passenger in list
	public void PassengerListToQueue() {
		TreeSet<Passenger> passengerList = PassengerList.getPassengerList();
		for(Passenger p: passengerList ) {
			String plName = p.getPassengerName().getLastName();
			String pbRef = Integer.toString(p.getBookingReference());
			String NameBRef = plName + pbRef;
			Thread t = new Thread(new PassengerInQueue());
			t.setName(NameBRef);
			t.start();
		}
	}

}

//	public void PassengerCheckIn(Passenger p, int Desk) {
//		// Do the checkin Things.
//		String name = p.getPassengerName().getFirstAndLastName();
//		//System.out.println(name + "Check in has happened");
//	}	
//	String name;
//	Thread t;
//	
//	PassengerThread (String thread){
//		name = "PassengerToQueue";  
//		t = new Thread(this, name);
//		System.out.println(" Passenger active. New thread: " + t);
//		t.start();
//	}		 
//
//	public void run() {
////		try {	
////			System.out.println(" PassengerThread running.");
////			// add code to include passenger here
////		}catch (InterruptedException e) {
////			System.out.println(name + "Interrupted");
////		}
//		System.out.println(name + " exiting.");
//	}

//
//public class DeskThread extends Thread{
//	String name;
//	Thread t;
//	DeskThread (String thread){
//		name = "CheckInDeskAdded";  
//		t = new Thread(this, name);
//		System.out.println(" Desk active. New thread: " + t);
//		t.start();
//	}	
//
//	public void run() {
//		try {
//			for(int i = 2; i > 0; i--) {
//				// add code so passenger "uses" desk
//				System.out.println(name + ": " + i);
//				Thread.sleep(1000);
//			}
//		}catch (InterruptedException e) {
//			System.out.println(name + "Interrupted");
//		}
//		System.out.println(name + " exiting.");
//	}
//}
//


//		    int availableSlots() {
//		        return Desk.availablePermits();
//		    }


//		class MultiThread {
//		public static void main(String args[]) {
//		     new MyThread("One");
//		     new MyThread("Two");
//		     new NewThread("Three");
//		try {
//		     Thread.sleep(10000);
//		} catch (InterruptedException e) {
//		      System.out.println("Main thread Interrupted");
//		}
//		      System.out.println("Main thread exiting.");
//		      }
//		}
//	

