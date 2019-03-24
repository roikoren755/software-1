package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends AbsBattleship {
	private static final int MAINTENANCE_COST = 2500;
	private static final int ENGINE_COST = 1000;

	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers,
	               List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	public int getAnnualMaintenanceCost(int maintenanceCost, int engineCost, int crewCost) {
		int annualMaintenanceCost = maintenanceCost;
		for (Weapon weapon : this.getWeapon())
			annualMaintenanceCost += weapon.getAnnualMaintenanceCost();
		annualMaintenanceCost += crewCost * this.getCrewMembers().size();
		annualMaintenanceCost += (int) (this.getMaximalSpeed() * engineCost);
		return annualMaintenanceCost;
	}

	public int getAnnualMaintenanceCost() {
		return this.getAnnualMaintenanceCost(Fighter.MAINTENANCE_COST, Fighter.ENGINE_COST, 0);
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