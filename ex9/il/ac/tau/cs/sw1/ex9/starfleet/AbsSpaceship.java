package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class AbsSpaceship implements Spaceship {
	private static final int FIREPOWER = 10;
	private String name;
	private int commissionYear;
	private float maximalSpeed;
	private Set<? extends CrewMember> crewMembers;

	public AbsSpaceship(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers) {
		this.name = name;
		this.commissionYear = commissionYear;
		this.maximalSpeed = maximalSpeed;
		this.crewMembers = crewMembers;
	}

	public String getName() {
		return this.name;
	}

	public int getCommissionYear() {
		return this.commissionYear;
	}

	public float getMaximalSpeed() {
		return this.maximalSpeed;
	}

	public Set<? extends CrewMember> getCrewMembers() {
		return this.crewMembers;
	}

	public int getFirePower() {
		return AbsSpaceship.FIREPOWER;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName());
		sb.append("\n\tName=").append(this.getName());
		sb.append("\n\tCommissionYear=").append(this.getCommissionYear());
		sb.append("\n\tMaximalSpeed=").append(this.getMaximalSpeed());
		sb.append("\n\tFirePower=").append(this.getFirePower());
		sb.append("\n\tCrewMembers=").append(this.getCrewMembers().size());
		sb.append("\n\tAnnualMaintenanceCost=").append(this.getAnnualMaintenanceCost());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		AbsSpaceship other = (AbsSpaceship) obj;
		if (this.getName() == null) {
			return other.getName() == null;
		} else return this.getName().equals(other.getName());
	}

	public int compareTo(Spaceship other) {
		if (this.getFirePower() > other.getFirePower())
			return -1;
		if (this.getFirePower() < other.getFirePower())
			return 1;
		if (this.getCommissionYear() > other.getCommissionYear())
			return -1;
		if (this.getCommissionYear() < other.getCommissionYear())
			return 1;
		return this.getName().compareTo(other.getName());
	}
}