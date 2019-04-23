package semanticQuizGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateContextObject {
	CreateModel model = new CreateModel();
	
	Map contextObj = new LinkedHashMap() {{
        put("label", "iri" );
}};

}
