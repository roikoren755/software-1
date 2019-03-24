package il.ac.tau.cs.sw1.hw3;

public class ArrayUtils {
	public static int[] reverseArray(int[] array) {
		int len = array.length;
		for (int i = 0; i < len / 2; i++) {
			int temp = array[i];
			array[i] = array[len - i - 1];
			array[len - i - 1] = temp;
		}

		return array;
	}

	public static int[] shiftArrayToTheRightCyclic(int[] array, int move) {
		if (move <= 0) {
			return array;
		}

		int len = array.length;
		int[] tempArray = new int[len];

		for (int i = 0; i < len; i++) {
			tempArray[(i + move) % len] = array[i];
		}

		for (int i = 0; i < len; i++) {
			array[i] = tempArray[i];
		}


		return array;
	}

	public static int alternateSum(int[] array) {
		/**
		 * Go through the array, checking maxSum for sequences starting with an even
		 * index and an odd index separately. In each step, keep max of last
		 * sum, last sum plus int, and that minus another int.
		 * If length is odd, check final int with first sum.
		 * Return max of all sums.
		 */
		int len = array.length;

		if (len == 0) {
			return 0;
		}

		if (len == 2) {
			return array[0] - array[1];
		}

		int[][] sums = new int[2][3];
		int maxSum = 0;

		sums[0][0] = array[0] - array[1];
		sums[1][0] = array[1] - array[2];
		maxSum = sums[0][0];

		for (int i = 3; i < len; i += 2) {
			sums[0][1] = sums[0][0] + array[i - 1];
			sums[0][2] = sums[0][1] - array[i];
			sums[0][0] = Math.max(sums[0][2] - sums[0][0], sums[0][2]);

			sums[1][1] = sums[1][0] + array[i];
			if (i + 1 < len) {
				sums[1][2] = sums[1][1] - array[i + 1];
				sums[1][0] = Math.max(sums[1][2] - sums[1][0], sums[1][2]);
			}
		}

		if (len % 2 != 0) {
			sums[0][0] = Math.max(sums[0][0], sums[0][0] + array[len - 1]);
		}

		maxSum = Math.max(Math.max(sums[0][0], sums[0][1]), Math.max(sums[1][0], sums[1][1]));

		return maxSum;
	}

	public static int findPath(int[][] m, int i, int j) {
		boolean[] seen = new boolean[m.length];
		return findPathRecursive(m, i, j, seen);

	}

	public static int findPathRecursive(int[][] m, int i, int j, boolean[] seen) {
		if (m[i][j] == 1) {
			return 1;
		}

		int len = seen.length;
		seen[i] = true;
		for (int k = 0; k < len; k++) {
			if (m[i][k] == 1 && !seen[k]) {
				if (findPathRecursive(m, k, j, seen) == 1) {
					return 1;
				}
			}
		}

		return 0;
	}
}
