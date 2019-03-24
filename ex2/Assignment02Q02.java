public class Assignment02Q02 {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		double sum = 0;

		for (int i = 0, sign = 1; i < n; i++, sign = -sign) {
			sum += sign / (double) (2 * i + 1);
		}

		double computedPi = 4.0 * sum;
		System.out.println(computedPi + " " + Math.PI);
	}
}