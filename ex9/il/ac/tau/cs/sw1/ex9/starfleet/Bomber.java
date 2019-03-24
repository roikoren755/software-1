package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends AbsBattleship {
	private static final int MAINTENANCE_COST = 5000;
	private static final double TECHINICIAN_REDUCTION = 0.1;
	private int numberOfTechnicians;

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers,
	              List<Weapon> weapons, int numberOfTechnicians) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.numberOfTechnicians = numberOfTechnicians;
	}

	public int getNumberOfTechnicians() {
		return this.numberOfTechnicians;
	}

	public int getAnnualMaintenanceCost() {
		double weaponMaintenanceCost = 0;
		for (Weapon weapon : this.getWeapon())
			weaponMaintenanceCost += weapon.getAnnualMaintenanceCost();
		weaponMaintenanceCost *= (Bomber.TECHINICIAN_REDUCTION * this.getNumberOfTechnicians());
		int annualMaintenanceCost = Bomber.MAINTENANCE_COST + (int) weaponMaintenanceCost;
		return annualMaintenanceCost;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\n\tNumberOfTechnicians=").append(this.getNumberOfTechnicians());
		return sb.toString();
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