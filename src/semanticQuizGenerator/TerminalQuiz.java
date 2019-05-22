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

public class TerminalQuiz {
	String country = "http://www.wikidata.org/entity/Q20";
	String countryLabel = "Norway";
	int points;
	
	


	 public TerminalQuiz(String countryIRI) throws JsonGenerationException, IOException {
		this.country = countryIRI;
		Model model = new CreateModel().CreateModel();
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



		Hints hints = new Hints(model, country);

		this.points = hints.size();

		System.out.println("START: ");
		System.out.println("This first hint is free: \n");
		for (String s: hints.hints) {
			System.out.println("---"+s+"---\n");

			Scanner reader = new Scanner(System.in);  // Reading from System.in
			String answer = reader.nextLine(); // Scans the next token of the input as an int.
			
			if (answer.equalsIgnoreCase(countryLabel)) {
				System.out.println("Correct Answer!");
				break;
			}
			
			this.points--;
		}
		System.out.println("Correct country: "+countryLabel+"\n");
	}




	public int getPoints() {
		return points;
	}
	 
	
}