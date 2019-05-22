package semanticQuizGenerator;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class CreateModel {

	/**
	 * creates the model by reading the JSON files, creating a context object
	 * @return Model
	 */
	public static Model createModel() {
		// reads the JSONS files
		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		String countriesJSON = JSONParser.readJson("src/data/countries.json");
		String countriesRankedByAreaJSON = JSONParser.readJson("src/data/countriesRankedByArea.json");
		
		//put all the JSON files in to one string
		String capCountJSON = countriesJSON.substring(0, countriesJSON.length()-2)+", "+capitalsJSON.substring(1);
		String allJSON = capCountJSON.substring(0, capCountJSON.length()-2)+","+countriesRankedByAreaJSON.substring(1);
		
		//
		CreateContextObject createContext = new CreateContextObject();
		Object jsonObj = null;
		try {
			jsonObj = JsonUtils.fromString(allJSON);
		} catch (IOException e) {
			e.printStackTrace();}

		//
		Object expandedObj = expandJSON(createContext.contextObj, jsonObj);
		String jsonStr;
		try {
			jsonStr = JsonUtils.toPrettyString(expandedObj);
			Model model = ModelFactory.createDefaultModel();
		    RDFDataMgr.read(model, new StringReader(jsonStr), "", Lang.JSONLD);
			return model;
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	/**
	 * 
	 * @param contextObj
	 * @param jsonObj
	 * @return
	 */
	public static Object expandJSON(Map contextObj, Object jsonObj) {
		 // create and set an options object
	    JsonLdOptions expandOptions = new JsonLdOptions("http://www.wikidata.org/wiki/");
	    expandOptions.setExpandContext(contextObj);
	  
	    //expand and flatten the JSON object
	    Object expandedObj = JsonLdProcessor.expand(jsonObj, expandOptions); 
	    Object flattenedExpandedObj = JsonLdProcessor.flatten(expandedObj, new JsonLdOptions());
		return flattenedExpandedObj;
	}
	
	/**
	 * Prints the model in turtle format
	 * @param model
	 */
	public void printModel(Model model) {
		model.write(System.out, "TURTLE");
	}
}
