import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Trip {
	
	private List <Passenger> passengers;
	private List <Driver> drivers;
	private Passenger currentPassenger;
	private Driver currentDriver;
	
	private final double RATE = 1.0;
	private final double SPEED = 1.2;
	private final double DRIVERSHARE = 0.8;
	
	public Trip () {
		passengers = new ArrayList<Passenger>();
		drivers = new ArrayList<Driver>();
	}
	
	public void addDriver(Driver driver) {
		if (driver.getLocation()[0] < 0 | driver.getLocation()[1] < 0) {
			System.out.println("INVALID LOCATION " + Arrays.toString(driver.getLocation()));
		}
		else if (driver.getBalance() < 0) {
			System.out.printf("INVALID STARTING BALANCE %.2f",driver.getBalance());
		}
		else {
			drivers.add(driver);
		}
	}
	
	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}
	
	public void run() {
		System.out.println("***BEGIN***\n\n");
		
		while (!passengers.isEmpty()) {
			currentPassenger = passengers.remove(0);
			if (isBalanceEnough(currentPassenger)) {
				System.out.println("         Passenger has enough money.");
				doTrip(currentPassenger);
			}
			else {
				System.out.println("         NOT ENOUGH MONEY, CANCELLED");
			}
		}
		System.out.println("\n\n***END***");
	}
	
	public boolean isBalanceEnough(Passenger passenger) {
		
		double fare = Helpers.computeFare(passenger.getLocation(), passenger.getDestination(), RATE);
		System.out.print("   " + passenger.getName() + " is located at " + Arrays.toString(passenger.getLocation()));
		System.out.print(" and is looking to go to " + Arrays.toString(passenger.getDestination()));
		System.out.printf(" which is %.2f away\n", Helpers.computeDistance(passenger.getLocation(), passenger.getDestination()));
		
		System.out.println("      Checking Money");
		System.out.printf( "         it will cost " + "$%.2f" + " to get to destination\n", fare);
		
		return (passenger.getBalance() >= fare);
	}
	
	public void doTrip(Passenger passenger) {
		System.out.println("   Trip started for " + passenger.getName());

		currentDriver = findDriver(passenger.getLocation());
		double pickupDistance = Helpers.computeDistance(passenger.getLocation(),currentDriver.getLocation());
		double pickupTime = pickupDistance * SPEED;
		
		double tripDistance = Helpers.computeDistance(passenger.getLocation(), passenger.getDestination());
		double tripTime = tripDistance * SPEED;
		
		int rating = passenger.rateDriver();
		
		double fare = Helpers.computeFare(passenger.getLocation(), passenger.getDestination(), RATE);

		System.out.print("   Closest driver is " + currentDriver.getName());
		System.out.printf(" and he is %.2f away\n", pickupDistance);
		
		System.out.printf(currentDriver.getName() + " starts driving and takes %.2f seconds to arrive\n", pickupTime);
		System.out.println(passenger.getName() + " gets picked up");
		System.out.printf(currentDriver.getName() + " drives " + "%.2f miles and takes %.2f seconds\n",tripDistance, tripTime);
		
		System.out.println(passenger.getName() + " arrives at " + Arrays.toString(passenger.getDestination()));
		currentDriver.setDestination(passenger.getDestination());

		System.out.println(passenger.getName() + " rates " + currentDriver.getName() + " a " +  rating);
		currentDriver.rateDriver((double)rating);
		
		passenger.subtractBalance(fare);
		currentDriver.addBalance(fare * DRIVERSHARE);
		
		System.out.printf(passenger.getName() + " pays " + "$%.2f to uber\n",fare);
		System.out.printf(passenger.getName() + " has $%.2f remaining\n", passenger.getBalance());

		
		System.out.printf(currentDriver.getName() + " receives $%.2f from uber\n",fare*DRIVERSHARE);
		
		System.out.printf(currentDriver.getName() + " now has $%.2f\n",currentDriver.getBalance());
		System.out.printf(currentDriver.getName() + " now has a rating of %.2f stars\n", currentDriver.getRating());
		System.out.println(currentDriver.getName() + " is ready for the next passenger");
		drivers.add(currentDriver);
		
	}
	
	public Driver findDriver(Integer[] destination) {
		
		Double driverDistance;
		Driver d1;
				
		for (int i = 0; i < drivers.size(); i++) {
			
			d1 = drivers.get(i);
			d1.setDestination(destination);
			driverDistance = Helpers.computeDistance(d1.getLocation(),destination);
			
			System.out.printf("      " + d1.getName() + " is " + "%.2f" + " away\n", driverDistance);
		}
		
		Collections.sort(drivers);
		
		return drivers.remove(0);
	}
	
	public List<Driver> getDrivers() {
		return drivers;
	}
	
	public List<Passenger> getPassengers() {
		return passengers;
	}
	

}
