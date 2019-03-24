package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter {
	private static final int STEALTH_ENGINE_COST = 50;
	private static int numberOfEngines = 0;

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers,
	                      List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		StealthCruiser.numberOfEngines++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers) {
		super(name, commissionYear, maximalSpeed, crewMembers, Arrays.asList(new Weapon("Laser Cannons",
				10, 100)));
		StealthCruiser.numberOfEngines++;
	}

	public int getAnnualMaintenanceCost() {
		int annualMaintenanceCost = super.getAnnualMaintenanceCost();
		annualMaintenanceCost += StealthCruiser.numberOfEngines * StealthCruiser.STEALTH_ENGINE_COST;
		return annualMaintenanceCost;
	}

	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}