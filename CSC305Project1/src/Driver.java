/**
 * Driver information
 * @author Michael Hilomen
 *
 */
public class Driver extends Entity {
	
	private String carTitle;
	private int numTrips;
	private double rating;
	
	
	/**
	 * Creates a new Driver object
	 * @param name Driver's name
	 * @param balance starting balance
	 * @param carTitle Driver's vehicle name
	 * @param location Driver's starting location
	 */
	public Driver (String name, double balance, String carTitle, MapLocation location) {
		super(name, balance, location);
		this.carTitle = carTitle;
		
		numTrips = 0;
		rating = 0.0;
		
	}
	
	/**
	 * Rates driver and updates driver average rating.
	 * @param tripRating driver's rating for the trip.
	 */
	public void rateDriver(double tripRating){
		rating = (((numTrips * rating) + tripRating)) / (numTrips + 1);
		numTrips++;
	}
	
	/**
	 * Shows the driver's vehicle
	 * @return driver's vehicle name
	 */
	public String getCarTitle() {
		return carTitle;
	}
	
	/**
	 * Shows driver rating
	 * @return driver's average rating
	 */
	public double getRating() {
		return rating;
	}
	
	public String toString() {
		
		String balance = String.format("$%.2f", getBalance());
		String r = String.format("%.2f stars", getRating());
		
		String s = "Driver Name: " + getName() + ", Car: " + getCarTitle() + ", Balance: " + balance
		+ ", Rating: " + r + ", Location: " + getLocation().toString();
		
		return s;
	}

}
