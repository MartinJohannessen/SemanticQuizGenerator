package semanticQuizGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
						+ "}", model)
				.execSelect();

		ArrayList<String> countries = new ArrayList();
		resultSet.forEachRemaining(qsol -> {
			countries.add(qsol.toString());
        	//System.out.println(qsol);
        });
		
		for(String country : countries) {
			System.out.println(country);
		}

		return countries;
	}

}
