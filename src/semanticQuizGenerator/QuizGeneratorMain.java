package semanticQuizGenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

public class QuizGeneratorMain {

	public static void main(String[] args) {

		CreateContextObject contextObject = new CreateContextObject();

		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		String countriesJSON = JSONParser.readJson("src/data/countries.json");
		String allJSON = countriesJSON.substring(0, countriesJSON.length()-2)+", "+capitalsJSON.substring(1);
		
		CreateContextObject createContext = new CreateContextObject();
		Object jsonObj = null;
		try {
			jsonObj = JsonUtils.fromString(allJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		Object expandedJSON = ExpandJSON.ExpandJSON(createContext.contextObj, jsonObj);
		
		

	}
}
