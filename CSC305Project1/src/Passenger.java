/**
 * Passenger information
 * @author Michael Hilomen
 *
 */
public class Passenger {
	
	private String name;
	private double balance;
	private Integer[] location;
	
	/**
	 * initializes new passenger.
	 * @param name Passenger name
	 * @param balance Balance
	 * @param location passenger's location
	 */
	
	public Passenger(String name, double balance, Integer[] location) {
		this.name = name;
		this.balance = balance;
		this.location = location;
	}
	
	/**
	 * Subtracts fare from passenger's balance.
	 * @param fare the trip's fare.
	 */
	public void subtractBalance(double fare) {
		balance -= fare;
	}
	/**
	 * Shows the passenger's balance.
	 * @return The passenger's balance.
	 */
	public double getBalance() {
		return balance;
	}

}
