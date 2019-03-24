public class Assignment02Q01Sec01 {
	public static void main(String[] args) {
		for (String c : args) {
			if (c.charAt(0) % 2 == 0)
				System.out.println(c);
		}
	}
}