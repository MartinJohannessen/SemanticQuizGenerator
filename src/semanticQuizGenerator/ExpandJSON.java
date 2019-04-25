package semanticQuizGenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class ExpandJSON {
	
	public ExpandJSON(CreateContextObject contextObject, Object jsonObj) {
		Map json = new LinkedHashMap();
	    if (jsonObj instanceof Map)
	        json = (Map) jsonObj;
	    json.put("@context", contextObject);
	    Object expandedObj = JsonLdProcessor.expand(json);
	    try {
			System.out.println(JsonUtils.toPrettyString(expandedObj));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
