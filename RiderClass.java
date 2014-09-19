public class RiderClass {

	public int riderCount;
	public int total;
	
	public RiderClass (int total) {
		this.riderCount = 0;
		this.total 		= total;
	}
	
	public void increment () {
		this.riderCount++;
	}
	
	public void decrement () {
		this.riderCount--;
	}
	
	public int get () {
		return this.riderCount;
	}
	
	public void set (int riderCount) {
		this.riderCount = riderCount;
	}
	
	public void setTotal (int total) {
		this.total = total;
	}
	
	public int getTotal () {
		return this.total;
	}
}
