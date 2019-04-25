package semanticQuizGenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class ExpandJSON {

	@SuppressWarnings("unchecked")
	public ExpandJSON(Map contextObj, Object jsonObj) {

		 // create and set an options object
	    JsonLdOptions expandOptions = new JsonLdOptions("http://www.wikidata.org/wiki/");
	    expandOptions.setExpandContext(contextObj);
	  
	    // expand the JSON object
	    Object expandedObj = JsonLdProcessor.expand(jsonObj, expandOptions);
	 
	    
	    try {
			System.out.println(JsonUtils.toPrettyString(expandedObj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
