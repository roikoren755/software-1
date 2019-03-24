package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B3 extends A3 {
	private static final long serialVersionUID = -239945428677266540L;
	private static int tries = 0;

	public B3(String s) {
		super(s);
	}

	public void foo(String s) throws B3 {
		if (B3.tries == 0)
			throw new B3(s);
	}

	public String getMessage() {
		return this.s;
	}
}