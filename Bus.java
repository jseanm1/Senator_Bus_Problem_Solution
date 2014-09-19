import java.util.concurrent.Semaphore;

public class Bus extends Thread {
	
	private Semaphore mutex;
	private Semaphore bus;
	private Semaphore boarded;
	private RiderClass riders;
	private int n;
	
	public Bus (Semaphore mutex, Semaphore bus, Semaphore boarded, RiderClass riders) {
		this.mutex		= mutex;
		this.bus		= bus;
		this.boarded	= boarded;
		this.riders		= riders;
	}
	
	public void run () {
		while (true) {
			try {
				mutex.acquire();				// Get the mutex. Hold on
				//System.out.println("Bus acquired mutex");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			n = Math.min(riders.get(), 50);				// Get the number of passengers (n) ready to be boarded
			//System.out.println(riders.get());
			for (int i=0;i<n;i++) {
				bus.release();					// Allow n passengers to be boarded
				//System.out.println("Bus released by Bus");
				try {
					boarded.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			riders.set(Math.max(riders.get()-50, 0));		// Set the new number of passengers waiting 
			
			mutex.release();					// Release the mutex
			
			depart(n);						// Depart
						
			riders.setTotal(riders.total - n);			// Set new total number of passengers boarded
			
			if (riders.getTotal() <= 0) {				// If all passengers are boarded, break the loop
				break;
			}
		}
	}
	
	public void depart(int n) {
		System.out.println ("Bus departed with " + n + " passengers");
	}

}
