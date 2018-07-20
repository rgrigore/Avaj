package avaj;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);

		Simulator.writer.print("JetPlane#" + this.name + "(" + this.id + "): ");

		switch (weather) {
			case "SUN" : coordinates.updateLatitude(10); coordinates.updateHeight(2); Simulator.writer.println("Hm, my solar powered jet is happy."); break;
			case "RAIN" : coordinates.updateLatitude(5); Simulator.writer.println("It's raining. Better watch out for lightnings."); break;
			case "FOG" : coordinates.updateLatitude(1); Simulator.writer.println("Do planes even have fog lights?"); break;
			case "SNOW" : coordinates.updateHeight(-7); Simulator.writer.println("OMG! Winter is coming!"); break;
		}

		if (coordinates.getHeight() > 100) {
			Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + "): " + "Dude, im way too high right now!");
			coordinates.setHeight();
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void land() {
		Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + ") [" + this.coordinates.getLongitude() + "; " + this.coordinates.getLatitude() + "; " + this.coordinates.getHeight() + "]: " + "A boring uneventful landing.");
		weatherTower.unregister(this);
		Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
	}

	public void crash() {
		Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + ") [" + this.coordinates.getLongitude() + "; " + this.coordinates.getLatitude() + "; " + this.coordinates.getHeight() + "]: " + "I feel the need, the need for speed! BOOM!!");
		weatherTower.unregister(this);
		Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
	}
}
