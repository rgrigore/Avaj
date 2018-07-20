package avaj;

public class Balloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Balloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);

		Simulator.writer.print("Balloon#" + this.name + "(" + this.id + "): ");

		switch (weather) {
			case "SUN" : coordinates.updateLongitude(2); coordinates.updateHeight(4); Simulator.writer.println("Let's enjoy the good weather and take some pics."); break;
			case "RAIN" : coordinates.updateHeight(-5); Simulator.writer.println("Damn you rain! You messed up my balloon."); break;
			case "FOG" : coordinates.updateHeight(-3); Simulator.writer.println("It seems i forgot my flashlight."); break;
			case "SNOW" : coordinates.updateHeight(-15); Simulator.writer.println("The extra weight is making me go down!"); break;
		}

		if (coordinates.getHeight() > 100) {
			Simulator.writer.println("Balloon#" + this.name + "(" + this.id + "): " + "How did i even get so high?!");
			coordinates.setHeight();
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.writer.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void land() {
		Simulator.writer.println("Balloon#" + this.name + "(" + this.id + ") [" + this.coordinates.getLongitude() + "; " + this.coordinates.getLatitude() + "; " + this.coordinates.getHeight() + "]: " + "Somehow i survived all that and landed.");
		weatherTower.unregister(this);
		Simulator.writer.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
	}

	public void crash() {
		Simulator.writer.println("Balloon#" + this.name + "(" + this.id + ") [" + this.coordinates.getLongitude() + "; " + this.coordinates.getLatitude() + "; " + this.coordinates.getHeight() + "]: " + "Welp, guess im dead. POOF!!");
		weatherTower.unregister(this);
		Simulator.writer.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
	}
}
