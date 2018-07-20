package avaj;

import java.io.*;

public class Simulator {
	public static PrintWriter writer;
	public static int cycles;

    public static void main(String[] args) {
    	if (args.length == 1) {
		    File simFile = new File("Simulation.txt");
		    try {
		    	writer = new PrintWriter(simFile);
		    } catch (FileNotFoundException fne) {
		        System.out.println("Error: " + fne.getMessage());
			    System.exit(0);
		    }
		    if (simFile.exists() && simFile.isFile()) {
		    	writer.print("");
		    }

		    WeatherTower weatherTower = new WeatherTower();

		    try {
		    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			    String readLine;
		    	int line = 0;

			    while ((readLine = br.readLine()) != null) {
					if (line == 0) {
						try {
							cycles = Integer.parseInt(readLine);
							if (cycles < 0) {
								throw new Exception("Sorry, we cannot go back in time!");
							}
						} catch (Exception ex) {
							System.out.println("Error: " + ex.getMessage());
							System.exit(0);
						}
					} else {
						try {
							String[] splitLine = readLine.split(" ");

							if (splitLine.length != 5) {
								throw new Exception(": line must have 5 parameters!");
							}

							try {
								AircraftFactory.newAircraft(
										splitLine[0],
										splitLine[1],
										Integer.parseInt(splitLine[2]),
										Integer.parseInt(splitLine[3]),
										Integer.parseInt(splitLine[4])
								).registerTower(weatherTower);
							} catch (NumberFormatException nfe) {
								throw new Exception(": parameter 3 to 5 must be integers.");
							} catch (NullPointerException npe) {
								System.out.println("Error: " + npe.getMessage());
								System.exit(0);
							}

						} catch (Exception ex) {
							System.out.println("Error on line " + line + ex.getMessage());
							System.exit(0);
						}
					}
					line++;
			    }
			    br.close();
		    } catch (Exception ex) {
		    	System.out.println("Error: " + ex.getMessage());
			    System.exit(0);
		    }

		    while (cycles-- > 0) {
		    	weatherTower.changeWeather();
		    }

		    writer.close();
		    System.exit(0);
		}
	    System.out.println("Please pass 1 and only 1 parameter! Thanks.");
    }
}
