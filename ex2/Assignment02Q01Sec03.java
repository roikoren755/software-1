public class Assignment02Q01Sec03 {
	public static void main(String[] args) {
		int[] remainders = new int[args.length];

		for (int i = 0; i < args.length; i++) {
			remainders[i] = Integer.parseInt(args[i]) % 6;
		}

		int evens = 0;

		for (int remainder : remainders) {
			if (remainder % 2 == 0)
				evens++;
		}

		System.out.println(evens);
	}
}