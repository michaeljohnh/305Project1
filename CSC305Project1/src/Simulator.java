public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		
		Trip t1 = new Trip();
		t1.addDriver(new Driver("Driver1", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		
		/*
		t1.addDriver(new Driver("Driver2", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver3", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver4", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver5", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver6", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver7", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver8", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver9", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		t1.addDriver(new Driver("Driver10", 100.0, "1999 Honda Civic", Helpers.createLocation()));
		*/

		t1.addPassenger(new Passenger("Passenger1", 500.0, Helpers.createLocation(),Helpers.createLocation()));
		t1.addPassenger(new Passenger("Passenger2", 200.0, Helpers.createLocation(),Helpers.createLocation()));
		t1.addPassenger(new Passenger("Passenger3", 100.0, Helpers.createLocation(),Helpers.createLocation()));
		t1.addPassenger(new Passenger("Passenger4", 400.0, Helpers.createLocation(),Helpers.createLocation()));
		t1.addPassenger(new Passenger("Passenger5", 300.0, Helpers.createLocation(),Helpers.createLocation()));

		t1.run();

	}

}
