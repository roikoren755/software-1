package src.il.ac.tau.cs.software1.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;

	private static void printError() {
		System.out.println("Unknown command. Please try again.");
	}

	private static void printResult() {
		System.out.print("The result is: ");
	}

	private static void printList(List<Person> lst) {
		System.out.print("[");
		for (int i = 0; i < lst.size(); i++) {
			Person p = lst.get(i);
			String rep = p.toString();
			if (i != lst.size() - 1)
				rep += ",";
			System.out.print(rep);
		}
		System.out.println("]");
	}

	public static List<Person> getPersonFromUser() {
		String inp = null;
		int persons = 0;
		System.out.println("Please choose number of persons (or X for exit):");
		while (inp == null) {
			inp = scanner.next();
			try {
				persons = Integer.parseInt(inp);
			} catch (NumberFormatException e) {
				if (inp.equals("X"))
					return null;
				printError();
				inp = null;
			}
		}
		List<Person> lst = new ArrayList<Person>();
		for (int i = 0; i < persons; i++) {
			System.out.print("Please enter age: ");
			int age = scanner.nextInt();
			System.out.print("Please enter name: ");
			String name = scanner.next();
			lst.add(i, new Person(age, name));
		}
		System.out.print("Your list is: ");
		printList(lst);
		return lst;
	}

	public static Predictor getPredictorFromUsers() {
		Predictor pred = null;
		String inp = "";
		char c = ' ';
		int n = 0;
		while (pred == null) {
			System.out.println("Please choose Predictor:");
			System.out.println("E - Even");
			System.out.println("B - Big");
			System.out.println("S - StartWith");
			inp = scanner.next();
			switch (inp) {
				case "E":
					pred = new Even();
					break;
				case "S":
					System.out.println("Please enter a char to compare:");
					c = scanner.next().charAt(0);
					pred = new StartWith(c);
					break;
				case "B":
					System.out.println("Please enter a number to compare with:");
					n = scanner.nextInt();
					pred = new Big(n);
					break;
				default:
					printError();
			}
		}
		return pred;
	}

	public static List<Person> apply(List<Person> lst, Predictor p) {
		Predicates preds = new Predicates(lst);
		String inp = "";
		while (!inp.equals("N")) {
			System.out.println("What would you like to do:");
			System.out.println("R - Remove");
			System.out.println("E - Retain");
			System.out.println("C - Collect");
			System.out.println("F - Find");
			System.out.println("N - Insert a new list");
			inp = scanner.next();
			switch (inp) {
				case "N":
					break;
				case "R":
					preds.remove(p);
					printResult();
					printList(preds.getLst());
					break;
				case "E":
					preds.retain(p);
					printResult();
					printList(preds.getLst());
					break;
				case "C":
					List<Person> newList = preds.collect(p);
					preds = new Predicates(newList);
					printResult();
					printList(preds.getLst());
					break;
				case "F":
					printResult();
					System.out.println(preds.find(p));
					break;
				default:
					printError();
			}
		}
		return getPersonFromUser();
	}

	public static void run() {
		while (true) {
			List<Person> lst = getPersonFromUser();
			if (lst == null)
				return;
			Predictor p = getPredictorFromUsers();
			List<Person> newLst = apply(lst, p);
			if (newLst == null)
				return;
		}
	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		run();
		scanner.close();
	}
}
