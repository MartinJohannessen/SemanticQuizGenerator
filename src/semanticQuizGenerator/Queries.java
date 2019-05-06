package semanticQuizGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;

public class Queries {
	Object country;
	static InfModel model;
	
	public Queries(InfModel model) {
		//ArrayList expanded = (ArrayList) expandedJSON;
		//this.country = expanded.get(10);
		//inkedHashMap object = (LinkedHashMap) this.country;
		//getCountry(object);
		this.model = model;
		query();
	}
	
	public static String getCountry(LinkedHashMap object) {
		String country = object.get("http://www.wikidata.org/wiki/Q6256").toString();
		country = cleanString(country);
		return country;
	}
	
	public static String cleanString(String string) {
		string = string.replace("[{@value=", "");
		string = string.replace("}]", "");
		return string;
	}
	
	public static void query() {
		ResultSet resultSet = QueryExecutionFactory
		        .create(""
		            + "SELECT ?s ?p ?o WHERE {"
		            + "    ?s ?p ?o ."
		            + "}", model)
		        .execSelect();

		    resultSet.forEachRemaining(qsol -> System.out.println(qsol.toString()));
	}

	
}
