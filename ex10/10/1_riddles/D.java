public class D implements Comparable<D> {
	private int key;
	private int intVal;
	private String stringVal;

	public D(int key, int intVal, String stringVal) {
		this.key = key;
		this.intVal = intVal;
		this.stringVal = stringVal;
	}

	public D(int key, int val) {
		this(key, val, "");
	}

	public D(int key, String val) {
		this(key, 0, val);
	}

	public int getIntVal() {
		return this.intVal;
	}

	public String getStringVal() {
		return this.stringVal;
	}

	@Override
	public int compareTo(D o) {
		if (o == null)
			return -1;
		if (this.key == o.key)
			return 0;
		return -1;
	}
}
