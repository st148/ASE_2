import java.util.TreeSet;
import java.util.concurrent.Semaphore;
import java.util.Arrays;

public class Airport {
	int numberOfDesks = 2;    
	private Semaphore freeDesk = new Semaphore(numberOfDesks);
	//creates array no. of desks long auto set to 0 (waiting)
	//we will change so 1 is serving and 2 is closed
	public int[] deskStatus = new int [numberOfDesks];

	class CheckIn{
		// defining which desk is in use
		public int currantDesk = 999; //error number
		//count the total number of check ins
		int noCheckIns = 0;
		public int getC () {return this.noCheckIns;};
		public void useDesk() {	
			// Include any check in code required for GUI.



			// add returnable desk status 
			System.out.println("using desk statuses "+Arrays.toString(deskStatus));

			//increase the count of checkins
			int n = this.noCheckIns; n++;
			this.noCheckIns = n;		// the counting is mostly for testing purposes rn
			String threadName = Thread.currentThread().getName();
			System.out.println("Check-In " + noCheckIns + " (" + threadName + ") at desk " + currantDesk);
		}  
		
		
		public int identifyFreeDeskNo() {
			int n = 999; //error number 
			for (int i = 0; i < deskStatus.length; i++) {
				if (deskStatus[i] == 0) {
					deskStatus[i] = 1;
					n = i+1; 
					this.currantDesk = n; //System.out.println("useing desk no.:-" + n + " [" + currantDesk + "]");
					break;
				}
			}
			return n;
		}		
		public void emptyIdentifiedDesknoNo(int n) {
			deskStatus[n-1] = 0; 
			this.currantDesk = 999; //System.out.println("emtied desk no.:-" + n + " [" + currantDesk + "]");
		}
	}

	CheckIn	checkin = new CheckIn();

	//Define a Passenger thread
	class PassengerInQueue  implements Runnable {
		public void run(){
			String NameBRef = Thread.currentThread().getName();
			System.out.println(NameBRef + " joined queue.");

			// add passenger to returnable queue list 

			try { 
				freeDesk.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// add removal from the queue list 
			
			int deskno = checkin.identifyFreeDeskNo();
			checkin.useDesk();
			checkin.emptyIdentifiedDesknoNo(deskno);
			freeDesk.release();
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


