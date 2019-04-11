package semanticQuizGenerator;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

public class QuizGeneratorMain {
	
	public static void main(String[] args) throws JsonParseException, IOException {
	
		CreateModel model = new CreateModel();
		
		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		
		 Object capitalsJsonObj = JsonUtils.fromString(capitalsJSON);

		
		
		
	}
}

