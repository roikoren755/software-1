package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;

public class SpaceshipComparator implements Comparator<Spaceship> {
	public SpaceshipComparator() {
	}

	public int compare(Spaceship ship1, Spaceship ship2) {
		return ship1.compareTo(ship2);
	}
}
