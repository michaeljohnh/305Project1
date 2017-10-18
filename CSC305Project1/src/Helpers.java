import java.util.Random;

public class Helpers {
	public static Integer[] createLocation() {
		Integer [] loc = new Integer[2];
		
		Random rand = new Random();
		
		loc[0] = rand.nextInt(300);
		loc[1] = rand.nextInt(300);
		
		return loc;
	}

	public static double computeDistance(Integer[] origin, Integer[] destination) {
		double distance;
		
		distance = (double)Math.sqrt(Math.pow((destination[0] - origin[0]),2) 
				+ Math.pow( (destination[1] - origin[1]) ,2));
		
		return distance;
	}

	public static double computeFare(Integer[] origin, Integer[] destination, double rate) {
		double distance;
		
		distance = (double)Math.sqrt(Math.pow((destination[0] - origin[0]),2) 
				+ Math.pow( (destination[1] - origin[1]) ,2));
		
		return distance;
	}
}
