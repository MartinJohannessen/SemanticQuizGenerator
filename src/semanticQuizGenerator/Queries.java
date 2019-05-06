package semanticQuizGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;

public class Queries {
	Object country;
	static Model model;
	
	public Queries(Model model) {
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
		            + "SELECT ?capital WHERE {"
		        	+ "		?c ?https://www.wikidata.org/wiki/Property:P36 ?capital."
		            + "}", model)
		        .execSelect();

		    resultSet.forEachRemaining(qsol -> System.out.println(qsol.toString()));
	}

	
}
