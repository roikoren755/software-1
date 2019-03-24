public class B {
	private int key;

	public B(int key, int val) {
		this.key = key;
	}

	public B(int key, String val) {
		this(key, 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + key;
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
		B other = (B) obj;
		return key == other.key;
	}
}
