package src.il.ac.tau.cs.software1.predicate;

public class Even implements Predictor {

	@Override
	/*
	 * (non-Javadoc)
	 * Return true iff (Person)o.age %2 == 0
	 */
	public boolean test(Object o) {
		if (o instanceof Person) {
			Person p = (Person) o;
			return p.getAge() % 2 == 0;
		}
		return false;
	}

}
