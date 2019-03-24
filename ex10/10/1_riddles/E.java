public class E {
	private int key;
	private int intVal;
	private String stringVal;

	public E(int key, int intVal, String stringVal) {
		this.key = key;
		this.intVal = intVal;
		this.stringVal = stringVal;
	}

	public E(int key, int val) {
		this(key, val, "");
	}

	public E(int key, String val) {
		this(key, 0, val);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intVal;
		result = prime * result + key;
		result = prime * result + ((stringVal == null) ? 0 : stringVal.hashCode());
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
		E other = (E) obj;
		if (intVal != other.intVal)
			return false;
		if (key != other.key)
			return false;
		if (stringVal == null) {
			return other.stringVal == null;
		} else return stringVal.equals(other.stringVal);
	}
}
