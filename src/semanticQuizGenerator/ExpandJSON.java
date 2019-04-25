package semanticQuizGenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class ExpandJSON {

	public ExpandJSON(CreateContextObject contextObject, Object jsonObj) {
		Map<String, CreateContextObject> json = new LinkedHashMap<String, CreateContextObject>();
		if (jsonObj instanceof Map)
			System.out.println("hey");
		json = (Map<String, CreateContextObject>) jsonObj;

		json.put("@context", contextObject);

		Object expandedObj = JsonLdProcessor.expand(json);
	}
}
