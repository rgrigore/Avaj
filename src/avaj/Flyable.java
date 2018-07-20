package avaj;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower weatherTower);
	Coordinates getCoordinates();
	void land();
	void crash();
}
