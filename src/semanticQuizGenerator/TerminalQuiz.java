package semanticQuizGenerator;

import java.io.IOException;
import java.util.Scanner;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;

import com.fasterxml.jackson.core.JsonGenerationException;

/**
 * terminal app for the semantic quiz generator
 */
public class TerminalQuiz {
	
	//the IRI of an country
	String country;
	//the name of the country
	String countryLabel;
	int points;
	
	/**
	 * prints the actual quiz to terminal
	 * 
	 * @param countryIRI the IRI of a country 
	 * @throws java.io.IOException if io fails
	 * @throws com.fasterxml.jackson.core.JsonGenerationException if it fails
	 */
	 public TerminalQuiz(String countryIRI) throws JsonGenerationException, IOException {
		this.country = countryIRI;
		
		//create a model from the JSON files
		Model model = CreateModel.createModel();
		
		// selects the name of the country from the IRI with a sparql query and substring
		ParameterizedSparqlString pss = new ParameterizedSparqlString();
		pss.setCommandText(""
				+ "SELECT ?s WHERE {"
				+ "     ?e <https://www.wikidata.org/wiki/Property:P17> ?s."
				+ "}");

		pss.setIri("e", country);
		Query query = pss.asQuery();
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model);  
		ResultSet resultSet = queryExecution.execSelect();
		resultSet.forEachRemaining(qsol -> {
			String s = qsol.toString();
			s = s.substring(8, s.length()-3); 
			this.countryLabel = s;
		});

		//get hints about a country by querying the model
		Hints hints = new Hints(model, country);
		this.points = hints.size();

		//print the quiz of a single country
		System.out.println("START: ");
		System.out.println("This first hint is free: \n");
		for (String s: hints.hints) {
			System.out.println("---"+s+"---\n");

			Scanner reader = new Scanner(System.in); 
			String answer = reader.nextLine();
			
			if (answer.equalsIgnoreCase(countryLabel)) {
				System.out.println("Correct Answer!");
				break;
			}
			
			this.points--;
		}
		System.out.print("The country was "+countryLabel+". ");
		System.out.println("You got "+points+" points this round out of "+ hints.size()+ " possible.\n");
	}


	public int getPoints() {
		return this.points;
	}
}