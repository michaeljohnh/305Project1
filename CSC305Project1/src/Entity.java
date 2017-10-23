
/**
 * Superclass for Driver and Client classes
 * @author Michael Hilomen
 *
 */
public abstract class Entity {
	
	private String name;
	private Double balance;
	private MapLocation location;
	private MapLocation destination;
	
	/**
	 * Instantiating a driver or client
	 * @param name Driver or Client name
	 * @param balance Driver or Client beginning balance
	 * @param location Driver or Client starting location
	 */
	public Entity(String name, Double balance, MapLocation location) {
		this.name = name;
		this.balance = balance;
		this.location = location;
	}
	
	/**
	 * Entity's name
	 * @return entity name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Entity's balance
	 * @return entity balance
	 */
	public Double getBalance() {
		return balance;
	}
	
	/**
	 * Entity's location
	 * @return location in x,y coordinates
	 */
	public MapLocation getLocation() {
		return location;
	}
	
	/**
	 * Sets the driver or client location
	 * @param location location in x,y coordinates
	 */
	public void setLocation(MapLocation location) {
		this.location = location;
	}
	
	/**
	 * Sets destination for driver or client
	 * @param destination location in x,y coordinates
	 */
	public void setDestination(MapLocation destination) {
		this.destination = destination;
	}
	
	/**
	 * Adds amount to balance
	 * @param payment amount to be added to balance
	 */
	public void addBalance(Double payment) {
		this.balance += payment;
	}
	
	/**
	 * Subtracts amount from balance
	 * @param charge amount to be subtracted from balance
	 */
	public void subtractBalance(Double charge) {
		this.balance -= charge;
	}
	
	/**
	 * shows driver or client destination
	 * @return Destination in x,y coordinates
	 */
	public MapLocation getDestination() {
		return destination;
	}
	
	/**
	 * Calculates distance between location and destination
	 * @return distance using distance formula
	 */
	public double computeDistance() {
		
		double distance;
	
		distance = (double)Math.sqrt(Math.pow((destination.getX() - location.getX()),2) 
				+ Math.pow( (destination.getY() - location.getY()) ,2));
		
		return distance;
	}

}
