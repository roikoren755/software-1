package il.ac.tau.cs.sw1.ex9.starfleet;

public class Cylon extends AbsCrewMember {
	private int modelNumber;

	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(name, age, yearsInService);
		this.modelNumber = modelNumber;
	}

	public int getModelNumber() {
		return this.modelNumber;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append(", modelNumber=").append(this.getModelNumber());
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