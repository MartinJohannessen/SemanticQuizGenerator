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
    static List<String> hints;
   
    public Hints(Model model, String countryIRI) {
        this.model = model;
        this.countryIRI = countryIRI;
        this.hints = new ArrayList<String>();
        addHints();
        
    }
    
    public int size() {
    	return hints.size();
    }
    
    public static void addHints() {
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P36", "capital");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P1082", "population");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P2044", "altitude of highest point");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P30", "continent");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P610", "highest point");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P122", "type of government");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P2250", "life expectancy");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P206", "located next to water");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P35", "head of state");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P47", "shares border with");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P2927", "water percent");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P2132", "GDP");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P3529", "income");
    	
    	//query(countryIRI, "https://www.wikidata.org/wiki/Property:P2046", "area");
    	
    }
   
    public static void query(String entity, String property, String subject) {
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
        return string;
    }
   
}