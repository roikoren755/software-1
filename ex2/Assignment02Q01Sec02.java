public class Assignment02Q01Sec02 {
	public static void main(String[] args) {
		int max_val = 0;
		int max_index = 0;

		for (int i = 0; i < args.length; i++) {
			int val = 0;
			int n = args[i].length();

			for (int j = 0; j < n; j++) {
				val += (int) args[i].charAt(j);
			}

			if (val >= max_val) {
				max_val = val;
				max_index = i;
			}
		}

		System.out.println(max_val + " " + args[max_index]);
	}
}