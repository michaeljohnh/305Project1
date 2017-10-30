import java.io.FileNotFoundException;

public class Simulator {

	public static void main(String[] args) throws FileNotFoundException {

		TripManager t1 = new TripManager();
		FileParser fp = new FileParser();

		t1 = fp.parseFile("samplerandom.txt");
		
		t1.run();

	}

}
