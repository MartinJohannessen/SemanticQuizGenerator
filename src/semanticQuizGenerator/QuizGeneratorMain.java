package semanticQuizGenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

public class QuizGeneratorMain {

	public static void main(String[] args) {

		CreateContextObject contextObject = new CreateContextObject();

		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		Object capitalsJsonObj = null;

		try {
			capitalsJsonObj = JsonUtils.fromString(capitalsJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
}
