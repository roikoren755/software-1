package src.il.ac.tau.cs.software1.predicate;

public class StartWith implements Predictor {

	private char c;

	public StartWith(char c) {
		this.c = c;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * Return true iff (Person)O.name.charAt(0) == c
	 */
	public boolean test(Object o) {
		if (o instanceof Person) {
			Person p = (Person) o;
			return p.getName().charAt(0) == c;
		}
		return false;
	}

}
