/**
 * Passenger information
 * @author Michael Hilomen
 *
 */

import java.util.Random;
public class Passenger extends Person{
	
	/**
	 * initializes new passenger.
	 * @param name Passenger name
	 * @param balance Balance
	 * @param location passenger's location
	 */
	
	public Passenger(String name, double balance, Integer[] location, Integer[] destination) {
		super(name, balance, location);
		setDestination(destination);
	}
	
	public int rateDriver() {
		
		Random r = new Random();
		int x = (int)r.nextGaussian() * 1 + 5;
		
		if (x > 5) {
			x = 5;
		}
		return x;
	}
	

}
