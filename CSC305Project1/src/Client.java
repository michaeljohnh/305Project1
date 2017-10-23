import java.util.Random;

/**
 * Contains information for Clients
 * @author Michael Hilomen
 *
 */
public class Client extends Entity{
	
	/**
	 * Creates a new Client object
	 * @param name Name of the client
	 * @param balance Client's starting balance
	 * @param location beginning location
	 * @param destination intended destination
	 */
	public Client(String name, double balance, MapLocation location, MapLocation destination) {
		super(name, balance, location);
		setDestination(destination);
	}
	
	/**
	 * Generates a rating for a driver, based on a normal distribution between 0-5.
	 * @return Integer between 0-5
	 */
	public int giveRating() {
		
		Random r = new Random();
		int x = 5 - (int)Math.abs(r.nextGaussian());
		
		return x;
	}
	
	public String toString() {
		String balance = String.format("$%.2f", getBalance());
		
		String s = "Client Name: " + getName() + ", Balance: " + balance + ", Destination: " + getDestination().toString() + ", Location: " + getLocation().toString() ;
		
		return s;
	}
}
