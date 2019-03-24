package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class StringUtils {
	public static String sortStringWords(String str) {
		String[] words = str.split(" ");
		Arrays.sort(words);
		int len = words.length - 1;
		String res = "";

		for (int i = 0; i < len; i++) {
			res += words[len - i] + " ";
		}
		res += words[0];

		return res;
	}

	public static String mergeStrings(String a, String b) {
		String[] aChars = a.split("");
		String[] bChars = b.split("");
		String res = "";

		for (String aChar : aChars) {
			for (String bChar : bChars) {
				if (aChar.charAt(0) == bChar.charAt(0)) {
					res += aChar;
					break;
				}
			}
		}

		return res;
	}

	public static boolean isAnagram(String a, String b) {
		String[] aChars = a.split("");
		String[] bChars = b.split("");
		int lenA = countChars(aChars), lenB = countChars(bChars);

		if (lenA != lenB) {
			return false;
		}

		for (String aChar : aChars) {
			if (aChar.charAt(0) == ' ') {
				continue;
			}
			char c = aChar.charAt(0);
			if (charCount(aChars, c) != charCount(bChars, c)) {
				return false;
			}
		}

		return true;
	}

	public static int countChars(String[] a) {
		int res = 0;
		for (String b : a) {
			if (b.charAt(0) != ' ') {
				res++;
			}
		}

		return res;
	}


	public static int charCount(String[] a, char c) {
		int res = 0;
		for (String aChar : a) {
			if (aChar.charAt(0) == c) {
				res++;
			}
		}

		return res;
	}
}
