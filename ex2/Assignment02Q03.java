public class Assignment02Q03 {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] fib = new int[n];
		float sum = 2F;
		fib[0] = 1;
		fib[1] = 1;

		System.out.println("The first " + n + " Fibonacci numbers are:");

		for (int i = 2; i < n; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
			sum += fib[i];
			System.out.print(fib[i] + " ");
		}

		System.out.println();
		System.out.println("The average is: " + (sum / n));
	}
}