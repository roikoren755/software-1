package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class AbsBattleship extends AbsSpaceship {
	private List<Weapon> weapons;

	public AbsBattleship(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers,
	                     List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
	}

	public List<Weapon> getWeapon() {
		return this.weapons;
	}

	public int getFirePower() {
		int firePower = super.getFirePower();
		for (Weapon weapon : this.weapons)
			firePower += weapon.getFirePower();
		return firePower;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\n\tWeaponArray=").append("[");
		String prefix = "";
		for (Weapon weapon : this.getWeapon()) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(weapon.toString());
		}
		sb.append("]");
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