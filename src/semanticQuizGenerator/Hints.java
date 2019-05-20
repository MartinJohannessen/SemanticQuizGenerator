package semanticQuizGenerator;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
 
public class Hints {
	static Model model;
    static String countryIRI;
    static String capitalIRI;
    static List<String> hints;
   
    public Hints(Model model, String countryIRI) {
        this.model = model;
        this.countryIRI = countryIRI;
        this.capitalIRI = getCapitalIRI();
        System.out.println(capitalIRI);
        this.hints = new ArrayList<String>();
        //addHints();
    }
    
    public int size() {
    	return hints.size();
    }
    
    public static void addHints() {
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P1082", "population");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P2044", "altitude of highest point");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P30", "continent");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P610", "highest point");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P122", "type of government");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P2250", "life expectancy");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P206", "located next to water");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P35", "head of state");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P47", "shares border with");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P2927", "water percent");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P2132", "GDP");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P3529", "income");
    	countryQuery(countryIRI, "https://www.wikidata.org/wiki/Property:P36", "capital");
    	countryQuery(countryIRI, "rankByArea", "rank by area");

    	countryQuery(capitalIRI, "https://www.wikidata.org/wiki/Property:P1082", "population of the capital");
    	countryQuery(capitalIRI, "https://www.wikidata.org/wiki/Property:P2044", "altitude of the highest point of the capital");
    	countryQuery(capitalIRI, "http://www.wikidata.org/wiki/Q1248784", "airport of the capital");
    	countryQuery(capitalIRI, "https://www.wikidata.org/wiki/Property:P206", "the capital is located next to");
    	//query(countryIRI, "https://www.wikidata.org/wiki/Property:P2046", "area");
    	
    }
    
    public static String getCapitalIRI() {
    	ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(""
        		+ "SELECT ?capital WHERE {"
				+ "		?capital <https://www.wikidata.org/wiki/Property:P1376> ?c." 
				+ "		?capital <https://www.wikidata.org/wiki/Property:P36> ?capitalLabel." 
				+ "		?country <https://www.wikidata.org/wiki/Property:P36> ?capitalLabel."
                + "}");
       
        pss.setIri("country", countryIRI);
        Query query = pss.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);  
        ResultSet resultSet = queryExecution.execSelect();
        resultSet.forEachRemaining(qsol -> {
        	capitalIRI = qsol.toString();
        	capitalIRI = capitalIRI.replace("( ?capital = <", "");
        	capitalIRI = capitalIRI.replace("> )", "");
        });
    	return capitalIRI;
    }
   
    public static void countryQuery(String entity, String property, String subject) {
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(""
                + "SELECT ?s WHERE {"
                + "     ?e ?p ?s."
                + "}");
       
        pss.setIri("e", entity);
        pss.setIri("p", property);
        Query query = pss.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);  
        ResultSet resultSet = queryExecution.execSelect();
        
        resultSet.forEachRemaining(qsol -> {
        	String s = qsol.toString();
        	s = cleanString(s, subject);
        	
        	
        	
        	
        	hints.add(s);
        });
    }
    
    public static String cleanString(String string, String subject) {
    	string = string.replace("( ?s = \"", subject + ": ");
    	string = string.replace("\" )", "");
    	if (subject.contains("GDP") || (subject.contains("income"))){
    		string = string + " US$";
    	}
    	
    	if (string.contains("altitude")){
    		string = string + " metres";
    	}
    	if (string.contains("population")){
    		string = string + " people";
    	}
    	if (string.contains("life")){
    		string = string + " years";
    	}
        return string;
    }
   
}