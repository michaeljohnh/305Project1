import java.util.Random;

public class Trip {
	private Integer [] location;
	private Integer [] destination;
	private Passenger passenger;
	private Driver driver;
	
	public Trip (Passenger passenger, Integer[] location, Integer[] destination) {
		this.passenger = passenger;
		this.location = location;
		this.destination = destination;
	}
	
	public void addDriver(Driver driver) {
		this.driver = driver;
	}
	
	public static Integer[] createLocation() {
		Integer [] loc = new Integer[2];
		
		Random rand = new Random();
		
		loc[0] = rand.nextInt(301);
		loc[1] = rand.nextInt(301);
		
		return loc;
	}
}
