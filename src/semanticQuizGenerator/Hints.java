package semanticQuizGenerator;
 
import java.text.NumberFormat;
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
        this.hints = new ArrayList<String>();
        createHints();
    }
    
    /**
     * 
     * @return the size of the list that contains all the hints about one country
     */
    public int size() {
    	return hints.size();
    }
    
    /**
     * creates all the hints for each country
     */
    public static void createHints() {
    	String iriBase = "https://www.wikidata.org/wiki/Property:";
    	query(countryIRI, iriBase + "P1082", "population");
    	query(countryIRI, iriBase + "P2044", "altitude of highest point");
    	query(countryIRI, iriBase + "P30", "continent");
    	query(countryIRI, iriBase + "P610", "highest point");
    	query(countryIRI, iriBase + "P122", "type of government");
    	query(countryIRI, iriBase + "P2250", "life expectancy");
    	query(countryIRI, iriBase + "P206", "located next to water");
    	query(countryIRI, iriBase + "P35", "head of state");
    	query(countryIRI, iriBase + "P47", "shares border with");
    	query(countryIRI, iriBase + "P2927", "water percent");
    	query(countryIRI, iriBase + "P2132", "GDP");
    	query(countryIRI, iriBase + "P3529", "income");
    	query(countryIRI, iriBase + "P36", "capital");
    	query(countryIRI, "http://www.semanticQuizGenerator.org/rankByArea", "rank by area");

    	query(capitalIRI, iriBase + "P1082", "population of the capital");
    	query(capitalIRI, iriBase + "P2044", "altitude of the highest point of the capital");
    	query(capitalIRI, "http://www.wikidata.org/wiki/Q1248784", "airport of the capital");
    	query(capitalIRI, iriBase + "P206", "the capital is located next to");  	
    }
    
    /**
     * queries the model to get the IRI of the capital of country 
     * @return capitalIRI
     */
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
    
    /**
     * remove parts of the string to make it more readable
     * add the subject to the string
     * @param string the string that is being cleaned
     * @param subject the subject from the query 
     * @return
     */
    public static String cleanString(String string, String subject) {
    	string = string.replace("( ?s = \"", subject + ": ");
    	string = string.replace("( ?s = ", subject + ": ");
    	string = string.replace("\" )", "");
    	string = string.replace(" )", "");
    	if (subject.contains("GDP") || (subject.contains("income"))){
    		string = string + " US$";
    	}
    	if (string.contains("altitude")){
    		string = string + " metres";
    	}
    	if (string.contains("population")){
    		//Formats the numbers to make them easier to read 
    		String newPopulation = string;
    		//remove all the letters from the string 
    		newPopulation = newPopulation.replace("population: ", "");
    		newPopulation = newPopulation.replace("population of the capital: ", "");
    		Double formattedPopulation = Double.parseDouble(newPopulation);
    		string = subject + ": " + NumberFormat.getInstance().format(formattedPopulation);
    	}
    	if (string.contains("life")){
    		string = string + " years";
    	}
        return string;
    }
   
}