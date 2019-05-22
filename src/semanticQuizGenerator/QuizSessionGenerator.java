package semanticQuizGenerator;

import java.util.ArrayList;
import java.util.Random;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;

public class QuizSessionGenerator {
	
	/**
	 * creates a list with random countries
	 * @param nQuizzes
	 * @return an ArrayList of strings containing n random countries 
	 */
	public static ArrayList<String> Session(int nQuizzes) {
		Model model = CreateModel.createModel();
		//selects all countries from the model
		ResultSet resultSet = QueryExecutionFactory
				.create(""
						+ "SELECT DISTINCT ?c  WHERE {"
						+ "		?c <https://www.wikidata.org/wiki/Property:P17> ?country."
						+ "		?c <https://www.wikidata.org/wiki/Property:P30> ?continent."
						+ "}", model)
				.execSelect();
		
		//a list containing all the countries in the model
		ArrayList<String> allCountries = new ArrayList<String>();
		resultSet.forEachRemaining(qsol -> {
			allCountries.add(cleanString(qsol.toString()));
        });
		
		//a list containing all countries in this quiz session
		ArrayList<String> countries = new ArrayList<String>();
		
		//adds random countries to the countries-list
		for (int i = 0; i<nQuizzes; i++) {
			int random = generateRandomInt(allCountries.size());
			countries.add(allCountries.get(random));
			allCountries.remove(random);
		}
		return countries;
	}
	
	/**
	 * generates a random int with a given upper range
	 * @param upperRange
	 * @return a random int between 0 and upperRanger
	 */
	public static int generateRandomInt(int upperRange){
		Random random = new Random();
		return random.nextInt(upperRange);
	}
	
	/**
	 * Removes part of the string so that the string only contains the IRI 
	 * @param string the result from the query
	 * @return string with only the IRI
	 */
	public static String cleanString(String string) {
    	string = string.replace("( ?c = <", "");
        string = string.replace("> )", "");
        return string;
    }

}
