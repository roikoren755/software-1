package il.ac.tau.cs.sw1.ex9.starfleet;

public class Officer extends CrewWoman {
	private OfficerRank rank;

	public Officer(String name, int age, int yearsInService, OfficerRank rank) {
		super(name, age, yearsInService);
		this.rank = rank;
	}

	public OfficerRank getRank() {
		return this.rank;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(", rank=").append(this.getRank());
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