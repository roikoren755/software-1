package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter {
	private static final int MAINTENANCE_COST = 4000;
	private static final int CREW_COST = 500;
	private static final int ENGINE_COST = 500;

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
	                     List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost(ColonialViper.MAINTENANCE_COST, ColonialViper.ENGINE_COST,
				ColonialViper.CREW_COST);
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