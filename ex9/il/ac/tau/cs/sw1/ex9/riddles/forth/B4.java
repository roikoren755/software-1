package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class B4 implements Iterator<String> {
	private String[] strings;
	private int k;
	private int curr;

	public B4(String[] strings, int k) {
		this.strings = strings;
		this.k = k;
		this.curr = 0;
	}

	@Override
	public boolean hasNext() {
		return this.curr < (this.k * this.strings.length);
	}

	@Override
	public String next() {
		this.curr++;
		return this.strings[(this.curr - 1) % this.strings.length];
	}


}
