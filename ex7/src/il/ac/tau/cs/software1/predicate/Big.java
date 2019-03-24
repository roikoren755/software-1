package src.il.ac.tau.cs.software1.predicate;

public class Big implements Predictor {

	private int value;

	public Big(int value) {
		this.value = value;
	}

	@Override
	/**
	 * Return true iff (Person)o.age > value
	 */
	public boolean test(Object o) {
		if (o instanceof Person) {
			Person p = (Person) o;
			return p.getAge() > this.value;
		}
		return false;
	}

}
