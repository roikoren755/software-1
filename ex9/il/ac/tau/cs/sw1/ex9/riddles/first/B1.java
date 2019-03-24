package il.ac.tau.cs.sw1.ex9.riddles.first;

public class B1 extends A1 {
	private C1 c = new C1();

	public boolean foo() {
		boolean secret = c.foo();
		return !secret;
	}
}
