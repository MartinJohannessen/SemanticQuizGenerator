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
    // a list containing all hints about one country
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
    	String iriBaseLocal = "http://www.semanticQuizGenerator.org/";
    	
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
    	olympicQuery(countryIRI);
    	
    	query(countryIRI, iriBaseLocal +"rankByArea", "world ranking by area");
    	query(capitalIRI, iriBaseLocal + "hasAirport", "name of capital airport");
    	
    	query(capitalIRI, iriBase + "P1082", "population of the capital");
    	query(capitalIRI, iriBase + "P2044", "altitude of the highest point of the capital");
    	query(countryIRI, iriBase + "P36", "capital");
    	query(capitalIRI, iriBase + "P206", "the capital is located next to");  	
    }
    
    /**
     * queries the model to get the IRI of the capital of a country 
     * @return capitalIRI
     */
    public static String getCapitalIRI() {
    	ParameterizedSparqlString pss = new ParameterizedSparqlString();
    	//selects a capitals IRI corresponding a to a specific country
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
        	//removes all the other parts of the string that is not the IRI
        	capitalIRI = capitalIRI.replace("( ?capital = <", "");
        	capitalIRI = capitalIRI.replace("> )", "");
        	
        });
        return capitalIRI;
    }
   
    /**
     * Selects information about a country or a capital, writes it as a hint
     * adds the hint to the list of hints
     * @param entity the country
     * @param property the property
     * @param object the object of the query
     */
    public static void query(String entity, String property, String object) {
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(""
                + "SELECT ?s WHERE {"
                + "     ?e ?p ?s."
                + "}");
        //sets the parameters into the query
        pss.setIri("e", entity);
        pss.setIri("p", property);
        
        //query the model
        Query query = pss.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);  
        ResultSet resultSet = queryExecution.execSelect();
        
        //for every result write is as a hint and add it to the list of hints
        resultSet.forEachRemaining(qsol -> {
        	String s = qsol.toString();
        	s = cleanString(s, object);
        	hints.add(s);
        });
    }
    
    /**
     * adds a hint to the list if the country has held the olympic games
     * @param entity the IRI of the country
     */
    public static void olympicQuery(String entity) {
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText(""
                + "SELECT ?event ?y WHERE {"
                + "     ?iri <https://www.wikidata.org/wiki/Property:P17> ?label."
                + "     ?s <https://www.wikidata.org/wiki/Property:P17> ?label."
                + "		?s <https://www.wikidata.org/wiki/Property:P31> ?event."
                + "     ?s <https://www.wikidata.org/wiki/Property:P585> ?y."
                + "}");
        //sets the parameters into the query
        pss.setIri("iri", entity);
        
        //query the model
        Query query = pss.asQuery();
        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);  
        ResultSet resultSet = queryExecution.execSelect();
        
        //for every result write is as a hint and add it to the list of hints
        resultSet.forEachRemaining(qsol -> {
        	String s = qsol.toString();
        	//strips the string so that it only contains event and year  
        	String event = s.substring(12, s.length()-19);
        	String year = s.substring(44, s.length()-3);
        	s = "event: " + event + " in year " + year;
        	hints.add(s);
        });
    }
    
    /**
     * remove parts of the string to make it more readable
     * add the object to the string
     * @param string the string that is being cleaned
     * @param object the object from the query 
     * @return string a cleaned string
     */
    public static String cleanString(String string, String object) {
    	string = string.replace("( ?s = \"", object + ": ");
    	string = string.replace("( ?s = ", object + ": ");
    	string = string.replace("\" )", "");
    	string = string.replace(" )", "");
    	if (object.contains("GDP") || (object.contains("income"))){
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
    		string = object + ": " + NumberFormat.getInstance().format(formattedPopulation);
    	}
    	if (string.contains("life")){
    		string = string + " years";
    	}
        return string;
    }
   
}