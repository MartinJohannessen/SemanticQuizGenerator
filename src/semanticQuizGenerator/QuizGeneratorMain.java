package semanticQuizGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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



		
		Model model = new CreateModel().CreateModel();
		List<String> session = QuizSessionGenerator.Session();
		
		
		
		Hints query = new Hints(model, "http://www.wikidata.org/entity/Q20");

	}
}
