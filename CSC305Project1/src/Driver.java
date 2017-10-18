/**
 * Driver information
 * @author Michael Hilomen
 *
 */
public class Driver extends Person implements Comparable<Driver>{
	
	private String carTitle;
	private boolean isAvailable;
	private int numTrips;
	private double rating;
	
	
	/**
	 * Initializes driver.
	 * @param name Name of driver
	 * @param balance Driver's balance
	 * @param carTitle Driver's vehicle name
	 */
	public Driver (String name, double balance, String carTitle, Integer[] location) {
		super(name, balance, location);
		this.carTitle = carTitle;
		
		isAvailable = true;
		numTrips = 0;
		rating = 0.0;
		
	}
	
	/**
	 * sets driver's status
	 * @param available whether or not driver is busy
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	/**
	 * Rates driver
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
	 * checks if the driver is available to take a trip.
	 * @return driver's status
	 */
	public boolean isAvailable(){
		return isAvailable;
	}
	
	public double getRating() {
		return rating;
	}

	@Override
	public int compareTo(Driver other) {
		// TODO Auto-generated method stub
		double d1distance = Helpers.computeDistance(getLocation(), getDestination());
		double d2distance = Helpers.computeDistance(other.getLocation(), other.getDestination());
	
		if (d1distance < d2distance) {
			return -1;
		}
		else if (d1distance > d2distance) {
			return 1;
		}
		
		else if (d1distance == d2distance) {
			if (rating < other.getRating()) {
				return 1;
			}
			else if (rating > other.getRating()) {
				return -1;
			}
		}
		
		return 0;
		
	}
	

}
