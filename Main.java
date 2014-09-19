import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
	

	public static void main (String arg[]) {
		System.out.println ("Please enter the number of passengers");
		
		Scanner scan = new Scanner(System.in);
		int riderCount = scan.nextInt();
		
		Semaphore mutex 	= new Semaphore(1);
		Semaphore bus		= new Semaphore(0);
		Semaphore boarded	= new Semaphore(0);
		RiderClass riders 	= new RiderClass(riderCount);
				
		Bus b = new Bus(mutex, bus, boarded, riders);
		b.start();
		
		for (int i=0;i<riderCount;i++) {
			new Rider(mutex, bus, boarded, riders).start();
		}		
		
	}
}
