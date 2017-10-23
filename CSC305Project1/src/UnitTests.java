import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTests {

	@Test
	public void checkClientName() {
		
		Client c = new Client("Name", 200.0, new MapLocation(), new MapLocation());
		
		assertEquals("Name",c.getName());
	}
	
	public void checkClientBalance() {
		Client c = new Client("Name", 200.0, new MapLocation(), new MapLocation());
		assertEquals(200,c.getBalance(),0);	

	}
	
	@Test
	public void checkSubtraction() {
		Client c = new Client("Name", 100.0, new MapLocation(), new MapLocation());
		
		c.subtractBalance(18.0);
		
		assertEquals(82,c.getBalance(),0);
	}
	
	@Test
	public void checkAddition() {
		Driver d = new Driver("Name", 100.0, "Civic", new MapLocation());
		
		d.addBalance(10.0);
		
		assertEquals(110,d.getBalance(),0);
	}
	
	@Test
	public void checkDistance() {
		
		Client c= new Client("Name",200.0, new MapLocation(0,0), new MapLocation(1,1));
		assertEquals(Math.sqrt(2.0),c.computeDistance(),0.001);
	}
	
	@Test
	public void testDriverRating() {
		Driver d = new Driver("Name", 100.0, "Civic", new MapLocation());
		
		d.rateDriver(5.0);
		d.rateDriver(4.0);
		d.rateDriver(5.0);
		d.rateDriver(4.0);
		
		assertEquals(4.5,d.getRating(),0);
	}
	
	@Test
	public void testDriverCarTitle() {
		Driver d = new Driver("Name", 100.0, "Civic", new MapLocation());
		
		assertEquals(d.getCarTitle(),"Civic");
	}
	
	@Test
	public void testMapX() {
		MapLocation m = new MapLocation(100,10);
		
		assertEquals(100,m.getX());
	}
	
	@Test
	public void testMapY() {
		MapLocation m = new MapLocation(100,10);
		
		assertEquals(10,m.getY());
	}
	
	@Test
	public void testMapName() {
		MapLocation m = new MapLocation(10,10,"woah");
		
		assertEquals("woah",m.getName());
	}
	
	@Test
	public void testBadLocation() {
		MapLocation m = new MapLocation (-1,0);
		
		assertFalse(m.isValidLocation());
	}
	
	@Test
	public void testZeroLocation() {
		MapLocation m = new MapLocation (0,0);
		
		assertTrue(m.isValidLocation());
	}
	
	@Test
	public void testGoodLocation() {
		MapLocation m = new MapLocation (1,1);
		
		assertTrue(m.isValidLocation());
	}
	

}
