/**
 * Driver information
 * @author Michael Hilomen
 *
 */
public class Driver {
	
	private String name;
	private double balance;
	private String carTitle;
	private boolean isAvailable;
	private int numTrips;
	private double rating;
	private Integer[] location;
	
	
	/**
	 * Initializes driver.
	 * @param name Name of driver
	 * @param balance Driver's balance
	 * @param carTitle Driver's vehicle name
	 */
	public Driver (String name, double balance, String carTitle, Integer[] location) {
		this.name = name;
		this.balance = balance;
		this.carTitle = carTitle;
		this.location = location;
		
		isAvailable = true;
		numTrips = 0;
		rating = 5.0;
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
	 * Adds trip fare to driver's balance
	 * @param fare payment to driver.
	 */
	public void addBalance(double fare) {
		balance += fare;
	}
	
	/**
	 * Shows drivers current balance.
	 * @return the driver's balance
	 */
	public double getBalance(){
		return balance;
	}
	
	/**
	 * Shows the driver's name
	 * @return the driver's name
	 */
	
	public String getName(){
		return name;
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
	

}
