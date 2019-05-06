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
        for (String s: hints) {
        	System.out.println(s);
        }
    }
    
    public static void addHints() {
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P36");
    	query(countryIRI, "https://www.wikidata.org/wiki/Property:P36");
    }
   
    public static void query(String entity, String property) {
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(""
                + "SELECT ?continent WHERE {"
                + "     ?e ?p ?continent."
                + "}");
       
        pss.setIri("e", entity);
        pss.setIri("p", property);
        Query query = pss.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);  
        ResultSet resultSet = queryExecution.execSelect();
        
        resultSet.forEachRemaining(qsol -> {
        	String s = qsol.toString();
        	s = cleanString(s);
        	hints.add(s);
        });
    }
    
    public static String cleanString(String string) {
        string = string.replace("( ?", "");
        string = string.replace(" = \"", ": ");
        string = string.replace("\" )", "");
        return string;
    }
   
}