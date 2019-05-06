package semanticQuizGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Queries {
	Object country;
	
	public Queries(Object expandedJSON) {
		ArrayList expanded = (ArrayList) expandedJSON;
		this.country = expanded.get(10);
		LinkedHashMap object = (LinkedHashMap) this.country;
		getCountry(object);
	}
	
	public static String getCountry(LinkedHashMap object) {
		String country = object.get("http://www.wikidata.org/wiki/Q6256").toString();
		country = cleanString(country);
		return country;
	}
	
	public static String cleanString(String string) {
		string = string.replace("[{@value=", "");
		string = string.replace("}]", "");
		return string;
	}

	
}
