public class C implements Comparable<C> {
	private int key;
	private int val;

	public C(int key, int val) {
		this.key = key;
		this.val = val;
	}

	public C(int key, String val) {
		this(key, Integer.parseInt(val));
	}

	@Override
	public int compareTo(C o) {
		if (o == null)
			return -1;
		if (this.key > o.key)
			return -1;
		if (this.key < o.key)
			return 1;
		if (this.val > o.val)
			return -1;
		if (this.val == o.val)
			return 0;
		return 1;
	}
}
