package semanticQuizGenerator;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

public class QuizGeneratorMain {
	
	public static void main(String[] args) {
	
		CreateModel model = new CreateModel();
		
		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		Object capitalsJsonObj = null;
		
		 try {
			capitalsJsonObj = JsonUtils.fromString(capitalsJSON);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		 try {
			System.out.println(JsonUtils.toPrettyString(capitalsJsonObj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}

