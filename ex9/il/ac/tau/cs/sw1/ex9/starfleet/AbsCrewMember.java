package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class AbsCrewMember implements CrewMember {
	private String name;
	private int age;
	private int yearsInService;

	public AbsCrewMember(String name, int age, int yearsInService) {
		this.name = name;
		this.age = age;
		this.yearsInService = yearsInService;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public int getYearsInService() {
		return this.yearsInService;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName()).append(" [");
		sb.append("name=").append(this.getName()).append(", ");
		sb.append("age=").append(this.getAge()).append(", ");
		sb.append("yearsInService=").append(this.getYearsInService());
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
		if (getClass() != obj.getClass())
			return false;
		AbsCrewMember other = (AbsCrewMember) obj;
		if (this.getName() == null) {
			return other.getName() == null;
		} else return this.getName().equals(other.getName());
	}
}