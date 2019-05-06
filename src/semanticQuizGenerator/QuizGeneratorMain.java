package semanticQuizGenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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
		
		String jsonStr = JsonUtils.toPrettyString(jsonObj);
		CreateModel model = new CreateModel(jsonStr);
		model.rdfsModel.write(System.out, "TURTLE");
		//Queries country = new Queries(expandedObj);
	}
}
