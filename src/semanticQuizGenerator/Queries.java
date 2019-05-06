package semanticQuizGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
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
		query("https://www.wikidata.org/wiki/Q811");
		getContinent("http://www.wikidata.org/entity/Q20");
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
	
	public static void query(String entity) {
		ResultSet resultSet = QueryExecutionFactory
		        .create(""
		            + "SELECT ?population WHERE {"
		        	+ "		<http://www.wikidata.org/entity/Q258> <https://www.wikidata.org/wiki/Property:P1082> ?population."
		            + "}", model)
		        .execSelect();

		    resultSet.forEachRemaining(qsol -> System.out.println(qsol.toString()));
	}
	
	public static void getContinent(String entity) {
		String country = entity;
		ParameterizedSparqlString pss = new ParameterizedSparqlString();
		pss.setCommandText(""
	            + "SELECT ?continent WHERE {"
	        	+ "		?c <https://www.wikidata.org/wiki/Property:P30> ?continent."
	            + "}");
		
		pss.setIri("c", country);
		
		
	    Query query = pss.asQuery();
	 
	    QueryExecution queryExecution = QueryExecutionFactory.create(query, model);   
	    ResultSet resultSet = queryExecution.execSelect();
		
	    resultSet.forEachRemaining(qsol -> System.out.println(qsol.toString()));
	}
	
}
