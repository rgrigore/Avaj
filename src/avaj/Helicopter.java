package avaj;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(this.coordinates);

		Simulator.writer.print("Helicopter#" + this.name + "(" + this.id + "): ");

		switch (weather) {
			case "SUN" : coordinates.updateLongitude(10); coordinates.updateHeight(2); Simulator.writer.println("This is hot."); break;
			case "RAIN" : coordinates.updateLongitude(5); Simulator.writer.println("Hm, a rotor makes for a good umbrella."); break;
			case "FOG" : coordinates.updateLongitude(1); Simulator.writer.println("Oh look, I'm making a fog tornado!"); break;
			case "SNOW" : coordinates.updateHeight(-12); Simulator.writer.println("My rotor is going to freeze!"); break;
		}

		if (coordinates.getHeight() > 100) {
			Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + "It's gonna be hard hitting my target from so high up.");
			coordinates.setHeight();
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void land() {
		Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + ") [" + this.coordinates.getLongitude() + "; " + this.coordinates.getLatitude() + "; " + this.coordinates.getHeight() + "]: " + "Bombing mission completed.");
		weatherTower.unregister(this);
		Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
	}

	public void crash() {
		Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + ") [" + this.coordinates.getLongitude() + "; " + this.coordinates.getLatitude() + "; " + this.coordinates.getHeight() + "]: " + "I'm spinning out of control! BOOM!!");
		weatherTower.unregister(this);
		Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
	}
}
