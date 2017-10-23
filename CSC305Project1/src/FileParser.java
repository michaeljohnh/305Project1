import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * File Parser for uber app
 * @author Michael Hilomen
 *
 */
public class FileParser {
	
	TripManager tm;
	
	/**
	 * 
	 * Instantiates a file parser
	 * 
	 */
	public FileParser() {
		tm = new TripManager();
	}
	
	/**
	 * 
	 * Parser through a specified file for use with Trip Manager.
	 * 
	 * @param inFileName input file name
	 * @return trip manager instance
	 * @throws FileNotFoundException
	 */
	public TripManager parseFile (String inFileName) throws FileNotFoundException {
		
		FileInputStream fs = new FileInputStream(inFileName);	
		Scanner sc = new Scanner(fs);
		
		String checkThis;
		
		String name;
		String carTitle;
		MapLocation loc, dest;
		double balance;
		int x,y;
		
		while (sc.hasNextLine()) {
			
			String line = sc.nextLine();
			Scanner ls = new Scanner(line);
			
			checkThis = ls.next();
			
			if (checkThis.equals("Driver")) {
				name = ls.next();
				balance = ls.nextDouble();
				carTitle = ls.next();
				if (ls.hasNext()) {
					x = ls.nextInt();
					y = ls.nextInt();
					loc = new MapLocation(x,y);
				}
				else {
					loc = new MapLocation();
				}
				tm.addDriver(new Driver(name,balance,carTitle,loc));
			}
			else if (checkThis.equals("Client")) {
				name = ls.next();
				balance = ls.nextDouble();
				
				if (ls.hasNext()) {
					x = ls.nextInt();
					y = ls.nextInt();
					loc = new MapLocation(x,y);
					
					x = ls.nextInt();
					y = ls.nextInt();
					dest = new MapLocation(x,y);
				}
				else {
					loc = new MapLocation();
					dest = new MapLocation();
				}
				tm.addClient(new Client(name, balance, loc, dest));
			}
						
			ls.close();
		}
		
		sc.close();
		return tm;
	}
}
