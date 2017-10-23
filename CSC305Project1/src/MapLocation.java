import java.util.Random;

/**
 * Class for Map Location.
 * 
 * @author Michael Hilomen
 *
 */
public class MapLocation {
	private int x;
	private int y;
	private String name;
	
	/**
	 * Instantiates a new Map Location
	 * @param x x-coordinate on map
	 * @param y y-coordinate on map
	 * @param name location name
	 */
	public MapLocation(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	/**
	 * Instantiates a new Map Location without location name
	 * @param x x-coordinate on map
	 * @param y y-coordinate on map
	 */
	public MapLocation(int x, int y) {
		this.x = x;
		this.y = y;
		name = "";
	}
	
	/**
	 * Instantiates a new valid map location
	 */
	public MapLocation() {
		Random r = new Random();
		x = r.nextInt(TripManager.CITYLIMIT + 1);
		y = r.nextInt(TripManager.CITYLIMIT + 1);
		name = "";
	}
	
	/**
	 * Returns x-coordinate of map location
	 * @return x-coordinate of map location
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns y-coordinate of map location
	 * @return y-coordinate of map location
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns map location name, if applicable
	 * @return map location name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns if location created is valid within limits
	 * @return true if location is valid, false otherwise
	 */
	public boolean isValidLocation() {
		if (x < 0 | y < 0 | x > TripManager.CITYLIMIT | y > TripManager.CITYLIMIT) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		String s = String.format("[%3d,%3d]", x,y);
		return s;
	}
}
