package semanticQuizGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

public class QuizGeneratorMain {

	public static void main(String[] args) throws JsonGenerationException, IOException {

		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		String countriesJSON = JSONParser.readJson("src/data/countries.json");
		String allJSON = countriesJSON.substring(0, countriesJSON.length()-2)+", "+capitalsJSON.substring(1);
		
		CreateContextObject createContext = new CreateContextObject();
		Object jsonObj = null;
		try {
			jsonObj = JsonUtils.fromString(allJSON);
		} catch (IOException e) {
			e.printStackTrace();}
		

		Object expandedObj = ExpandJSON.ExpandJSON(createContext.contextObj, jsonObj);
		
		String jsonStr = JsonUtils.toPrettyString(expandedObj);
	    Model model = ModelFactory.createDefaultModel();
	    RDFDataMgr.read(model, new StringReader(jsonStr), "", Lang.JSONLD);
	    //model.write(System.out, "TURTLE");

		Hints query = new Hints(model, "http://www.wikidata.org/entity/Q20");
	}
}
