package semanticQuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;

public class QuizGeneratorMain {

	public static void main(String[] args) throws JsonGenerationException, IOException {
		
		System.out.println("Please enter the number of countries you would like in your quiz: ");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int nr = reader.nextInt(); // Scans the next token of the input as an int.


		System.out.println("RULES: ");
		System.out.println("Enter your answer below. It's not case sensitive.");
		System.out.println("If you enter the wrong answer, or nothing, a new hint will be provided.\n");
		System.out.println("For each hint you get, you will be subtracted one point.");
		System.out.println("Only the full official name of a country is recognized.");
		System.out.println("Example: 'pEopLes Republic of ChinA' is correct, 'China' is not.\n");
		
		int points = 0;
		ArrayList<String> countries = QuizSessionGenerator.Session(nr);
		for (String s: countries) {
			TerminalQuiz quiz = new TerminalQuiz(s);
			points+=quiz.getPoints();
		}
		
		System.out.println("You have finished your rounds with "+points+" points");
	}
}
