package avaj;

abstract public class AircraftFactory {
	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
		switch (type) {
			case "Helicopter" : return new Helicopter(name, new Coordinates(longitude, latitude, height));
			case "JetPlane" : return new JetPlane(name, new Coordinates(longitude, latitude, height));
			case "Balloon" : return new Balloon(name, new Coordinates(longitude, latitude, height));
			default: System.out.println("Error: Unknown aircraft type \"" + type + "\""); System.exit(0);
		}
		throw new NullPointerException();
	}
}
