package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter {
	private static final int MAINTENANCE_COST = 3500;
	private static final int CREW_COST = 500;
	private static final int ENGINE_COST = 1200;

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
	                   List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost(CylonRaider.MAINTENANCE_COST, CylonRaider.ENGINE_COST,
				CylonRaider.CREW_COST);
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