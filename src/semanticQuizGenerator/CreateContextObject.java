package semanticQuizGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateContextObject {
	String iriBase = "http://www.wikidata.org/wiki/";
	String propertyBase = "https://www.wikidata.org/wiki/Property:";
	Map contextObj;

	public CreateContextObject() {
		// Create context object for the resources
		contextObj = new LinkedHashMap() {
			{
				put("countryIRI", "@id");
				put("capitalIRI", "@id");
				put("country", iriBase + "Q6256");
				put("capital", iriBase + "Q5119");
				put("continent", iriBase + "Q5107");
				put("highestPoint", iriBase + "Q3393392");
				put("altitude", iriBase + "Q190200");
				put("population", iriBase + "Q2625603");
				put("area", iriBase + "Q11500");
				put("government", iriBase + "Q7188");
				put("life expectancy", iriBase + "Q188419");
				put("bodyOfWater", iriBase + "Q15324");
				put("headOfState", iriBase + "Q48352");
				put("waterPercent", iriBase + "Water percent");
				put("GDP", iriBase + "Q12638");
				put("income", iriBase + "Q1527264");
				put("inception", iriBase + "Q24574747");
				put("airport", iriBase + "Q1248784");

				put("country", propertyBase + "P17");
				put("capital", propertyBase + "P36");
				put("continent", propertyBase + "P30");
				put("highest", propertyBase + "P610");
				put("altitude", propertyBase + "P2044");
				put("population", propertyBase + "P1082");
				put("area", propertyBase + "P2046");
				put("government", propertyBase + "P122");
				put("lifeExpectancy", propertyBase + "P2250");
				put("locatedNextToWater", propertyBase + "P206");
				put("headofState", propertyBase + "P35");
				put("sharesBorderWith", propertyBase + "P47");
				put("waterPercent", propertyBase + "P2927");
				put("GDP", propertyBase + "P2132");
				put("income", propertyBase + "P3529");
				put("inception", propertyBase + "P571");
			}
		};

	}
}