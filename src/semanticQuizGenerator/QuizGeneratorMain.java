package semanticQuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonGenerationException;

public class QuizGeneratorMain {

	public static void main(String[] args) throws JsonGenerationException, IOException {


		//TerminalQuiz quiz = new TerminalQuiz("http://www.wikidata.org/entity/Q148");
		//TerminalQuiz quiz2 = new TerminalQuiz("http://www.wikidata.org/entity/Q20");
		//TerminalQuiz quiz3 = new TerminalQuiz("http://www.wikidata.org/entity/Q17");
		//TerminalQuiz quiz4 = new TerminalQuiz("http://www.wikidata.org/entity/Q16");
		
		ArrayList<String> countries = QuizSessionGenerator.Session(5);
		for (String s: countries) {
			TerminalQuiz quiz = new TerminalQuiz(s);
		}
	}
}
