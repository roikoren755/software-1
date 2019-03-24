package il.ac.tau.cs.sw1.ex9.starfleet;

public class CrewWoman extends AbsCrewMember {
	public CrewWoman(String name, int age, int yearsInService) {
		super(name, age, yearsInService);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
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