package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Scanner;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	public static String[] scanVocabulary(Scanner scanner) { // Q - 1
		String[] vocabulary = new String[3000];
		int i = 0;
		while (scanner.hasNext() && i < 3000) {
			boolean toAdd = true;
			String word = scanner.next().toLowerCase();
			int len = word.length();
			for (int j = 0; j < len; j++) {
				if (word.charAt(j) < 'a' || word.charAt(j) > 'z') {
					toAdd = false;
				}
			}

			int j = 0;
			while (toAdd && vocabulary[j] != null && j < 3000) {
				if (word.equals(vocabulary[j])) {
					toAdd = false;
				}
				j++;
			}

			if (toAdd) {
				vocabulary[i] = word;
				i++;
			}
		}
		vocabulary = java.util.Arrays.copyOf(vocabulary, i);
		java.util.Arrays.sort(vocabulary);

		return vocabulary;
	}

	public static boolean isInVocabulary(String[] vocabulary, String word) { // Q - 2
		if (java.util.Arrays.binarySearch(vocabulary, word) < 0) {
			return false;
		}

		return true;
	}

	public static boolean isLegalPuzzleStructure(char[] puzzle) {  // Q - 3
		for (char c : puzzle) {
			if (c != HIDDEN_CHAR && (c < 'a' || c > 'z')) {
				return false;
			}
		}

		return countHiddenInPuzzle(puzzle) > 0;
	}

	public static int countHiddenInPuzzle(char[] puzzle) { // Q - 4
		int cnt = 0;
		for (char c : puzzle) {
			if (c == HIDDEN_CHAR) {
				cnt++;
			}
		}

		return cnt;
	}

	public static boolean checkSolution(char[] puzzle, String word) { // Q - 5
		if (puzzle.length != word.length()) {
			return false;
		}

		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] != HIDDEN_CHAR && puzzle[i] != word.charAt(i)) {
				return false;
			} else {
				for (char c : puzzle) {
					if (c == word.charAt(i) && puzzle[i] == HIDDEN_CHAR) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public static String checkSingleSolution(char[] puzzle, String[] vocabulary) { // Q - 6
		String firstSolution = null;
		for (String solution : vocabulary) {
			if (checkSolution(puzzle, solution)) {
				if (firstSolution != null) {
					return null;
				}
				firstSolution = solution;
			}
		}

		return firstSolution;
	}

	public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
		int correctGuesses = 0;
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == HIDDEN_CHAR && guess == solution.charAt(i)) {
				puzzle[i] = guess;
				correctGuesses++;
			}
		}

		return correctGuesses;
	}

	public static void main(String[] args) throws Exception { //Q - 8
		if (args.length == 0) {
			System.out.println("No filepath entered. Please supply one as the first commandline argument");
			return;
		}

		String[] vocabulary = scanVocabulary(new Scanner(new File(args[0])));
		printReadVocabulary(args[0], vocabulary.length);

		printSettingsMessage();
		Scanner scanner = new Scanner(System.in);
		printEnterPuzzleMessage();
		char[] puzzle;
		do {
			String line = scanner.next().trim();
			int len = line.length();
			puzzle = new char[len];
			for (int i = 0; i < len; i++)
				puzzle[i] = line.charAt(i);
			if (!isLegalPuzzleStructure(puzzle)) {
				printIllegalPuzzleMessage();
			} else {
				printIllegalSolutionsNumberMessage();
			}
		} while (!isLegalPuzzleStructure(puzzle) || checkSingleSolution(puzzle, vocabulary) == null);

		printGameStageMessage();
		String solution = checkSingleSolution(puzzle, vocabulary);
		int lives = countHiddenInPuzzle(puzzle) + 3;
		for (int i = 0; i < lives; i++) {
			printPuzzle(puzzle);
			printEnterYourGuessMessage();
			char guess = scanner.next().trim().charAt(0);
			int correctGuesses = applyGuess(guess, solution, puzzle);
			if (correctGuesses > 0) {
				if (countHiddenInPuzzle(puzzle) == 0) {
					printWinMessage();
					break;
				} else {
					printCorrectGuess(lives - i - 1);
				}
			} else {
				printWrongGuess(lives - i - 1);
			}

			if (lives - i - 1 == 0) {
				printGameOver();
			}
		}

		scanner.close();
	}


	/*************************************************************/
	/*********************  Don't change this ********************/
	/*************************************************************/

	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterPuzzleMessage() {
		System.out.println("Enter your puzzle:");
	}

	public static void printIllegalPuzzleMessage() {
		System.out.println("Illegal puzzle, try again!");
	}

	public static void printIllegalSolutionsNumberMessage() {
		System.out.println("Puzzle doesn't have a single solution, try again!");
	}


	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}
}
