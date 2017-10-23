import java.util.*;
import java.io.*;
import org.json.*;

/**
 * Main class for managing uber trips.
 * 
 * @author Michael Hilomen
 *
 */
public class TripManager {

	private ArrayList <Driver> drivers;
	private LinkedList <Client> clients;
	private LinkedList <Client> finishedClients;
	
	private Client currentClient;
	private Driver currentDriver;
	@SuppressWarnings("rawtypes")
	private Map tripInfo;
	@SuppressWarnings("rawtypes")
	private Map dInfo, cInfo;
	
	private int numSuccessTrips;
	private int numFailTrips;
	private double totalTransactions;
	
	static final double RATE = 2.0;
	static final double SPEED = 40;
	static final double DRIVERSHARE = 0.8;
	static final int CITYLIMIT = 299;
		
	/**
	 * Instantiates a new trip manager.
	 */
	public TripManager() {
		clients = new LinkedList<Client>();
		drivers = new ArrayList<Driver>();
		finishedClients = new LinkedList<Client>();
		
		numSuccessTrips = 0;
		numFailTrips = 0;
		totalTransactions = 0.0;
	}
	
	/**
	 * Runs all trips based on all drivers and passengers
	 * 
	 */
	public void run() {
		
		System.out.println("***BEGIN***");
		
		while (!clients.isEmpty() && !drivers.isEmpty()) {
			
			currentClient = clients.pop();

			System.out.printf("%s, Distance: %.2f\n", currentClient.toString(), currentClient.computeDistance());
			
			currentDriver = findNearestDriver(currentClient);

			if (hasEnoughMoney(currentClient, currentDriver)) {
				numSuccessTrips++;
				try {
					runTrip();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			else {
				numFailTrips++;
				writeFailedTrip();
			}
			finishedClients.add(currentClient);

			drivers.add(currentDriver);
		}
		
		try {
			reportTrip();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Runs a single valid trip.
	 * @throws JSONException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void runTrip() throws JSONException {
		
		MapLocation driverOrigin = currentDriver.getLocation();
		MapLocation clientOrigin = currentClient.getLocation();
		
		double distanceDriverToClient = currentDriver.computeDistance();
		double distanceClientToDest = currentClient.computeDistance();
		double totalDistance = currentClient.computeDistance() + currentDriver.computeDistance();
		double fare = computeFare(currentClient,currentDriver);
		double timeToClient = currentDriver.computeDistance() * ( 1.0 / SPEED);
		double timeToDest = currentClient.computeDistance() / SPEED;
		double totalTime = timeToClient + timeToDest;
		
		currentDriver.setLocation(currentClient.getDestination());
		currentClient.setLocation(currentClient.getDestination());
		
		int rating = currentClient.giveRating();
		currentClient.subtractBalance(fare);
		currentDriver.addBalance(fare*DRIVERSHARE);
		currentDriver.rateDriver((double)rating);
		totalTransactions += fare;

		System.out.printf("Trip %d Started.\n",numSuccessTrips + numFailTrips);
				
		System.out.println("  " + currentDriver.getName() + " drives from " + driverOrigin.toString() 
		+ " to " + currentClient.getName() + " at " + clientOrigin.toString());
		System.out.printf("      Distance: %.2f miles, Elapsed Time: %.2f hours\n",distanceDriverToClient, timeToClient);
		
		System.out.println("  " + currentDriver.getName() + " drives from " + clientOrigin.toString() + " to " + currentClient.getDestination().toString());
		System.out.printf("      Distance: %.2f miles, Elapsed Time: %.2f hours\n",distanceClientToDest, timeToDest);
	
		System.out.printf("  Total distance: %.2f, Total time: %.2f hours\n",totalDistance,totalTime);
		System.out.printf("  Total fare: $%.2f\n",fare);
		
		
		System.out.printf("  " + currentClient.getName() + " pays $%.2f\n",fare);
		System.out.printf("  " + currentClient.getName() + " now has $%.2f remaining\n", currentClient.getBalance());
		
		System.out.printf("  " + currentDriver.getName() + " gets paid $%.2f\n", fare*DRIVERSHARE);
		System.out.printf("  " + currentDriver.getName() + " now has $%.2f balance\n",currentDriver.getBalance());
		
		
		System.out.println("  " + currentClient.getName() + " rates " + currentDriver.getName() + " " + rating + " stars");
		System.out.printf("  " + currentDriver.getName() + " is now rated %.2f stars\n",currentDriver.getRating());
		
		System.out.println("  " + currentDriver.getName() + " is now waiting for the next passenger in " + currentClient.getDestination().toString());

		tripInfo = new LinkedHashMap();
		cInfo = new LinkedHashMap();
		dInfo = new LinkedHashMap();
		
		String dBalance = String.format("$%.2f", currentDriver.getBalance());
		String cBalance = String.format("$%.2f", currentClient.getBalance());
		String dRating = String.format("%.2f", currentDriver.getRating());
		String tFare = String.format("%.2f", fare);
		String tDistance = String.format("%.2f", totalDistance);
		String tTime = String.format("%.2f", totalTime);
		
		dInfo.put("Name", currentDriver.getName());
		dInfo.put("Origin", driverOrigin.toString());
		dInfo.put("Location", currentDriver.getLocation().toString());
		dInfo.put("Balance", dBalance);
		dInfo.put("Rating", dRating);
		
		cInfo.put("Name", currentClient.getName());
		cInfo.put("Balance", cBalance);
		cInfo.put("Origin", clientOrigin.toString());
		cInfo.put("Location", currentClient.getLocation().toString());
		
		tripInfo.put("Trip Status", "SUCCESS");
		tripInfo.put("Driver",dInfo);
		tripInfo.put("Passenger", cInfo);
		tripInfo.put("Fare", tFare);
		tripInfo.put("Total Distance", tDistance);
		tripInfo.put("Total Time", tTime);
		
		JSONObject jObj = new JSONObject(tripInfo);
		
		String JSONFileName = String.format("trip%03d.json", numSuccessTrips + numFailTrips);
		
		PrintWriter toJSON;
		try {
			toJSON = new PrintWriter(JSONFileName);
			toJSON.print(jObj.toString());
			
			toJSON.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		


	}
	
	/**
	 * Runs a failed trip
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void writeFailedTrip() {
		
		tripInfo = new LinkedHashMap();
		cInfo = new LinkedHashMap();
		dInfo = new LinkedHashMap();
		
		String dBalance = String.format("$%.2f", currentDriver.getBalance());
		String cBalance = String.format("$%.2f", currentClient.getBalance());
		String dRating = String.format("%.2f", currentDriver.getRating());
		String tFare = String.format("%.2f", 0.0);
		String tDistance = String.format("%.2f", 0.0);
		String tTime = String.format("%.2f", 0.0);
		
		dInfo.put("Name", currentDriver.getName());
		dInfo.put("Origin", currentDriver.getLocation().toString());
		dInfo.put("Location", currentDriver.getLocation().toString());
		dInfo.put("Balance", dBalance);
		dInfo.put("Rating", dRating);
		
		cInfo.put("Name", currentClient.getName());
		cInfo.put("Balance", cBalance);
		cInfo.put("Origin", currentClient.getLocation().toString());
		cInfo.put("Location", currentClient.getLocation().toString());
		
		tripInfo.put("Trip Status", "FAIL");
		tripInfo.put("Driver",dInfo);
		tripInfo.put("Passenger", cInfo);
		tripInfo.put("Fare", tFare);
		tripInfo.put("Total Distance", tDistance);
		tripInfo.put("Total Time", tTime);
		
		JSONObject jObj = new JSONObject(tripInfo);
		
		String JSONFileName = String.format("trip%03d.json", numSuccessTrips + numFailTrips);
		
		PrintWriter toJSON;
		try {
			toJSON = new PrintWriter(JSONFileName);
			toJSON.print(jObj.toString());
			
			toJSON.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a report.txt file containing final driver and client information, as well as
	 * total transactions and trips.
	 * @throws FileNotFoundException
	 */
	public void reportTrip() throws FileNotFoundException {
		
		PrintWriter toText = new PrintWriter("report.txt");

		String s = String.format("   Successful Trips: %2d\n   Failed Trips: %6d\n", numSuccessTrips,numFailTrips);

		for (Driver d : drivers) {
			System.out.println(d.toString());
			toText.println(d.toString());
		}
		
		for (Client c : finishedClients) {
			System.out.println(c.toString());
			toText.println(c.toString());
		}
		
		System.out.print(s);
		System.out.printf("   Total Amount of Transactions: $%.2f\n",totalTransactions);
		
		toText.print(s);
		toText.printf("   Total Amount of Transactions: $%.2f\n",totalTransactions);
		
		toText.close();

	}

	/**
	 * Adds driver to list
	 * @param driver driver to be added to list
	 */
	public void addDriver(Driver driver) {
		
		if (!driver.getLocation().isValidLocation()) {
			System.out.println("Cannot add driver. Invalid Location " + driver.getLocation().toString());
		}
		else if (driver.getBalance() < 0) {
			System.out.printf("Cannot add driver. Invalid balance %.2f\n",driver.getBalance());
		}
		else {
			drivers.add(driver);
		}
	}
	
	/**
	 * Adds a client to list
	 * @param client client to be added to list
	 */
	public void addClient(Client client) {
		if (!client.getLocation().isValidLocation()) {
			System.out.println("Cannot add client. Invalid location " + client.getLocation().toString());
		}
		else if (!client.getDestination().isValidLocation()) {
			System.out.println("Cannot add client. Invalid destination "+ client.getDestination().toString());
		}
		else if (client.getBalance() < 0) {
			System.out.println("Cannot add client. Negative balance");
		}
		else {
			clients.add(client);
		}
	}
	
	
	/**
	 * finds nearest drivers based on nearest distance
	 * @param client client that needs a driver
	 * @return driver closest to client
	 */
	public Driver findNearestDriver(Client client) {
		
		System.out.println("Finding nearest driver.");
		
		Driver d;
		int nearestDriver = 0;
		double bestDistance = Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < drivers.size(); i++) {
			d = drivers.get(i);
			d.setDestination(client.getLocation());
			
			System.out.printf("  " + d.toString() + " is %.2f away.\n",d.computeDistance());
			
			if (bestDistance > d.computeDistance()) {
				nearestDriver = i;
				bestDistance = d.computeDistance();
			}
			else if (bestDistance == d.computeDistance()) {
				if (drivers.get(nearestDriver).getRating() < d.getRating()) {
					nearestDriver = i;
				}
			}
		}
		return drivers.remove(nearestDriver);
	}
	
	/**
	 * Calculates total fare amount
	 * @param client client in trip
	 * @param driver driver who will service client
	 * @return fare amount
	 */
	public double computeFare(Client client, Driver driver) {
		
		double totalDistance = client.computeDistance() + driver.computeDistance();
		return totalDistance * RATE;
	}
	
	/**
	 * Checks if client has enough money to pay the fare
	 * @param client client to check for fare money
	 * @param driver driver intended for client
	 * @return true if client has enough funds, false otherwise.
	 */
	public boolean hasEnoughMoney (Client client, Driver driver) {
		System.out.println("Checking if client has enough funds.");
		
		if (client.getBalance() >= computeFare(client,driver)) {
			System.out.println("  Client has enough funds.");
			return true;
		}
		
		System.out.println("   NOT ENOUGH FUNDS");
		return false;
	}
}
