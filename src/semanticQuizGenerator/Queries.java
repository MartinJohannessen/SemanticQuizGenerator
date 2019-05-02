package semanticQuizGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Queries {
	
	public static void createQuery(Object expandedJSON) {
		ArrayList<String> expanded = (ArrayList<String>) expandedJSON;
		getCountry(expanded.get(2));
	}
	
	public static void getCountry(Object object) {
		LinkedHashMap hashMap = (LinkedHashMap) object;
		/*ArrayList country = (ArrayList) hashMap.get("http://www.wikidata.org/wiki/Q6256");
		String string = (String) country.get(0);
		string.replace("[{@value=", "");
		string.replace("}]", "");
		string = "The country is " + string;
		System.out.println(string);*/
		System.out.println(hashMap.get("http://www.wikidata.org/wiki/Q6256"));
	}
}
