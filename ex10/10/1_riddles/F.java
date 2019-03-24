public class F implements Comparable<F> {
	private int key;
	private int intVal;
	private String stringVal;

	public F(int key, int intVal, String stringVal) {
		this.key = key;
		this.intVal = intVal;
		this.stringVal = stringVal;
	}

	public F(int key, int val) {
		this(key, val, "");
	}

	public F(int key, String val) {
		this(key, 0, val);
	}

	@Override
	public int compareTo(F o) {
		if (o == null)
			return -1;
		if (this.key > o.key)
			return -1;
		if (this.key < o.key)
			return 1;
		if (this.intVal > o.intVal)
			return -1;
		if (this.intVal < o.intVal)
			return 1;
		return this.stringVal.compareTo(o.stringVal);
	}
}
