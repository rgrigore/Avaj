package avaj;

import java.util.Random;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};
	private static int rand = (new Random()).nextInt(55);

	public static WeatherProvider getProvider() {
		return weatherProvider;
	}

	private WeatherProvider() {
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int seed = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + WeatherProvider.rand) / 4;

		return weather[seed % weather.length];
	}
}
