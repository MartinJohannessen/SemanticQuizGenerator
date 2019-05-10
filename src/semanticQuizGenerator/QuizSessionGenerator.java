package semanticQuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;

import com.fasterxml.jackson.core.JsonGenerationException;

public class QuizSessionGenerator {
	public static ArrayList<String> Session(int number) throws JsonGenerationException, IOException {
		Model model = new CreateModel().CreateModel();

		ResultSet resultSet = QueryExecutionFactory
				.create(""
						+ "SELECT DISTINCT ?c  WHERE {"
						+ "		?c <https://www.wikidata.org/wiki/Property:P17> ?country."
						+ "		?c <https://www.wikidata.org/wiki/Property:P30> ?continent."
						+ "}", model)
				.execSelect();

		ArrayList<String> allCountries = new ArrayList<String>();
		ArrayList<String> countries = new ArrayList<String>();
		resultSet.forEachRemaining(qsol -> {
			allCountries.add(cleanString(qsol.toString()));
        });
		
		for (int i = 0; i<number; i++) {
			int random = generateRandomInt(allCountries.size());
			countries.add(allCountries.get(random));
			allCountries.remove(random);
		}
		return countries;
	}
	

	public static int generateRandomInt(int upperRange){
		Random random = new Random();
		return random.nextInt(upperRange);
	}
	
	public static String cleanString(String string) {
    	string = string.replace("( ?c = <", "");
        string = string.replace("> )", "");
        return string;
    }

}
