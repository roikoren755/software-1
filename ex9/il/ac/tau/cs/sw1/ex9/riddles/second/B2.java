package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B2 extends A2 {
	private boolean bool;

	public B2() {
		this(true);
	}

	public B2(boolean bool) {
		this.bool = bool;
	}

	public String foo(String s) {
		if (bool)
			return s.toUpperCase();
		else
			return s.toLowerCase();
	}

	public A2 getA(boolean bool) {
		return new B2(bool);
	}
}
