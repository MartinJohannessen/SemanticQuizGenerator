package semanticQuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class of the Semantic Quiz Generator
 * 
 * @author      143
 * @author      137
 * @author      139
 */
public class QuizGeneratorMain {

	public static void main(String[] args) {

		//ask user for how many countries they would like in their quiz
		System.out.println("Please enter the number of countries you would like in your quiz: ");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int nr = 0;
		final int maxNr = 71; // initialises the highest possible number of countries the quiz will generate
		while (reader.hasNextInt()) {
			int input = reader.nextInt(); // Scans the next token of the input as an int
			while (input < 0) {
				System.out.println("Enter a whole postive number");
				input = reader.nextInt();
			}
			if (input <= maxNr) nr = input; //checks if the input does not exceed the highest possible number
			else {
				nr = maxNr; // adjusts the number to the maximum
				System.out.println("Number of countries requested is higher than the maximum. Set to "+nr+".");
			}
			break;
		}
		
		//print the rules for the game
		System.out.println("RULES: ");
		System.out.println("Enter your answer below. It's not case sensitive.");
		System.out.println("If you enter the wrong answer, or nothing, a new hint will be provided.\n");
		System.out.println("For each hint you get, you will be subtracted one point.");
		System.out.println("Only the full official name of a country is recognized.");
		System.out.println("Example: 'pEopLes Republic of ChinA' is correct, 'China' is not.\n");

		//sum of the point collected from all rounds
		int points = 0;
		
		//this is the ArrayList that holds the quiz session. For multiplayer functionality save countries and points to a database.
		ArrayList<String> countries;
		countries = QuizSessionGenerator.Session(nr);
		for (String s: countries) {
			TerminalQuiz quiz = new TerminalQuiz(s);
			points += quiz.getPoints();
		}
		System.out.println("You have finished your rounds with " + points + " points");
	}
}
