
public abstract class Person {
	
	private String name;
	private double balance;
	private Integer[] location;
	private Integer[] destination;
	
	public Person(String name, double balance, Integer[] location) {
		this.name = name;
		this.balance = balance;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void addBalance(double amount) {
		balance += amount;
	}
	
	public void subtractBalance(double amount) {
		balance -= amount;
	}
	
	public Integer[] getLocation() {
		return location;
	}
	
	public void setDestination(Integer[] destination) {
		this.destination = destination;
	}
	
	public Integer[] getDestination() {
		return destination;
	}
}
