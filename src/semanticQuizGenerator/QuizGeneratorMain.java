package semanticQuizGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

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
