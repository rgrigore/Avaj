package avaj;

public class Coordinates {
	private int longitude, latitude, height;

	Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getHeight() {
		return height;
	}

	public void updateLongitude(int longitude) {
		this.longitude += longitude;
	}

	public void updateLatitude(int latitude) {
		this.latitude += latitude;
	}

	public void updateHeight(int height) {
		this.height += height;
		if (this.height < 0) {
			this.height = 0;
		}
	}

	public void setHeight() {
		this.height = 100;
	}
}
