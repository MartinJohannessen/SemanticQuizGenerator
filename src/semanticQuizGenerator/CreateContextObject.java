package semanticQuizGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateContextObject {
	String entityBase = "http://www.wikidata.org/wiki/";
	String propertyBase = "https://www.wikidata.org/wiki/Property:";
	Map contextObj;

	public CreateContextObject() {
		//Add all properties to the context object
		this.contextObj = new LinkedHashMap<String, String>() {
			{
				put("countryIRI", "@id");
				put("capitalIRI", "@id");

				put("country", propertyBase + "P17");
				put("capital", propertyBase + "P36");
				put("capitalOf", propertyBase + "P1376");
				put("continent", propertyBase + "P30");
				put("highest", propertyBase + "P610");
				put("altitude", propertyBase + "P2044");
				put("population", propertyBase + "P1082");
				put("area", propertyBase + "P2046");
				put("government", propertyBase + "P122");
				put("lifeExpectancy", propertyBase + "P2250");
				put("locatedNextToWater", propertyBase + "P206");
				put("headOfState", propertyBase + "P35");
				put("sharesBorderWith", propertyBase + "P47");
				put("waterPercent", propertyBase + "P2927");
				put("GDP", propertyBase + "P2132");
				put("income", propertyBase + "P3529");
				put("inception", propertyBase + "P571");
				put("airport", entityBase + "Q1248784");
				put("rankByArea", "http://www.semanticQuizGenerator.org/" + "rankByArea");
			}
		};

	}
}