package semanticQuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class of the Semantic Quiz Generator
 * 
 * @author      Karoline Øijorden
 * @author      Martin Johannessen
 * @author      Tor Stølsnes
 */
public class QuizGeneratorMain {

	public static void main(String[] args) {

		//ask user for how many countries they would like in their quiz
		System.out.println("Please enter the number of countries you would like in your quiz: ");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int nr = reader.nextInt(); // Scans the next token of the input as an int
		
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
		try {
			countries = QuizSessionGenerator.Session(nr);
			for (String s: countries) {
				TerminalQuiz quiz = new TerminalQuiz(s);
				points += quiz.getPoints();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("You have finished your rounds with " + points + " points");
	}
}
