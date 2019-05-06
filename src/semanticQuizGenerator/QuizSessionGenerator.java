package semanticQuizGenerator;

import java.io.IOException;
import java.util.List;

import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;

import com.fasterxml.jackson.core.JsonGenerationException;

public class QuizSessionGenerator {
	public static List<String> Session() throws JsonGenerationException, IOException {
		Model model = new CreateModel().CreateModel();

		ResultSet resultSet = QueryExecutionFactory
				.create(""
						+ "SELECT DISTINCT ?country WHERE {"
						+ "		?c <http://www.wikidata.org/wiki/Property:P17> ?country."
						+ "}", model)
				.execSelect();

		//resultSet.forEachRemaining(qsol -> System.out.println(qsol.toString()));
		List<String> countries = resultSet.getResultVars();
		for(String country : countries) {
			System.out.println(country);
		}


		return null;
	}

}
