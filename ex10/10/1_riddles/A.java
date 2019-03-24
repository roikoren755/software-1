public class A {
	private int key;
	private int val;

	public A(int key, int val) {
		this.key = key;
		this.val = val;
	}

	public A(int key, String val) {
		this(key, Integer.parseInt(val));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + key;
		result = prime * result + val;
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
		A other = (A) obj;
		if (key != other.key)
			return false;
		return val == other.val;
	}


}
