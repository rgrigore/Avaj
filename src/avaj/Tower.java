package avaj;

import java.util.ArrayList;
import java.util.List;

public class Tower {
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable) {
		observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		observers.remove(flyable);
	}

	protected void conditionsChanged() {
		Simulator.writer.println();
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
			if (observers.get(i).getCoordinates().getHeight() <= 0) {
				observers.get(i--).land();
			}
		}
		for (int i = 0; i < observers.size(); i++) {
			Boolean crash = false;
			for (int j = i + 1; j < observers.size(); j++) {
				if (observers.get(i).getCoordinates() == observers.get(j).getCoordinates()) {
					observers.get(j--).crash();
					crash = true;
				}
			}
			if (crash) {
				observers.get(i--).crash();
			}
		}
	}
}
