package semanticQuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonGenerationException;

public class QuizGeneratorMain {

	public static void main(String[] args) throws JsonGenerationException, IOException {
		ArrayList<String> countries = QuizSessionGenerator.Session(5);
		for (String s: countries) {
			TerminalQuiz quiz = new TerminalQuiz(s);
		}
	}
}
