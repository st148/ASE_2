import java.util.concurrent.Semaphore;

public class AirportCheckIn {

class PassengerThread  implements Runnable {
	String name;
	Thread t;
	PassengerThread (String thread){
		name = "PassengerToQueue";  
		t = new Thread(this, name);
		System.out.println(" Passenger active. New thread: " + t);
		t.start();
	}		 

	public void run() {
		try {	 
			// add code to include passenger here
		}catch (InterruptedException e) {
			System.out.println(name + "Interrupted");
		}
		System.out.println(name + " exiting.");
	}
}

public class DeskThread extends Thread{
	String name;
	Thread t;
	DeskThread (String thread){
		name = "CheckInDeskAdded";  
		t = new Thread(this, name);
		System.out.println(" Desk active. New thread: " + t);
		t.start();
	}	

	public void run() {
		try {
			for(int i = 2; i > 0; i--) {
				// add code so passenger "uses" desk
				System.out.println(name + ": " + i);
				Thread.sleep(1000);
			}
		}catch (InterruptedException e) {
			System.out.println(name + "Interrupted");
		}
		System.out.println(name + " exiting.");
	}
}
}

public void PassengerCheckIn(Passenger p, int Desk) {
	// Do the checkin Things.
String name = p.getPassengerName().getFirstAndLastName();
System.out.println(name + "Check in has happened");
}	

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



}
