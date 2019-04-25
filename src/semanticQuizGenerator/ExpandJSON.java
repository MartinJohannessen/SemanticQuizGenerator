package semanticQuizGenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class ExpandJSON {

	@SuppressWarnings("unchecked")
	public ExpandJSON(Map contextObj, Object jsonObj) {

		Map json = new LinkedHashMap();
	    if (jsonObj instanceof Map) {
	    	System.out.println("hey");
	        json = (Map) jsonObj;
	    }
	    json.put("@context", contextObj);
	    Object expandedObj = JsonLdProcessor.expand(json);
	    
	    try {
			System.out.println(JsonUtils.toPrettyString(expandedObj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
