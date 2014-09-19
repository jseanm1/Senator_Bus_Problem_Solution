import java.util.concurrent.Semaphore;

public class Rider extends Thread {
	private Semaphore mutex;
	private Semaphore bus;
	private Semaphore boarded;
	private RiderClass riders;
	
	public Rider (Semaphore mutex, Semaphore bus, Semaphore boarded, RiderClass riders) {
		this.mutex		= mutex;
		this.bus		= bus;
		this.boarded		= boarded;
		this.riders		= riders;
	}
	
	public void run () {
		try {
			mutex.acquire();					// Get the mutex
			//System.out.println("Rider acquired mutex");
			riders.increment();					// Increase the number of passengers ready to be boarded by 1
			//System.out.println("Riders incremented " + riders.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mutex.release();						// Release the mutex
		
		try {
			bus.acquire();						// Get the bus so that the passenger can board
			//System.out.println("Rider acquired bus");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		board();							// Board the bus
		
		boarded.release();
	}
	
	public void board () {
		System.out.println("A passenger boarded");
	}

}
