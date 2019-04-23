package semanticQuizGenerator;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;

public class CreateModel {
	InfModel rdfsModel;
	String iriBase;
	
	public CreateModel() {
		Model rdfModel = ModelFactory.createDefaultModel();
        this.rdfsModel = ModelFactory.createRDFSModel(rdfModel);
        this.iriBase = "http://www.wikidata.org/wiki/";
        addResources();
		addProperties();
	}
	
	public void addResources() {
		Resource resCountry = rdfsModel.createResource(iriBase + "Q6256");
        Resource resCapital = rdfsModel.createResource(iriBase + "Q5119");
        Resource resContinent = rdfsModel.createResource(iriBase + "Q5107");
        Resource resHighestPoint = rdfsModel.createResource(iriBase + "Q3393392");
        Resource resAltitude = rdfsModel.createResource(iriBase + "Q190200");
        Resource resPopulation = rdfsModel.createResource(iriBase + "Q2625603");
        Resource resArea = rdfsModel.createResource(iriBase + "Q11500");
        Resource resGovernment = rdfsModel.createResource(iriBase + "Q7188");
        Resource resLifeExpectancy = rdfsModel.createResource(iriBase + "Q188419");
        Resource resBodyOfWater = rdfsModel.createResource(iriBase + "Q15324"); 
        Resource resHeadOfState = rdfsModel.createResource(iriBase + "Q48352");
        Resource resWaterPercent = rdfsModel.createResource(iriBase + "Water percent");
        Resource resGDP = rdfsModel.createResource(iriBase + "Q12638");
        Resource resIncome = rdfsModel.createResource(iriBase + "Q1527264");
        Resource resInception = rdfsModel.createResource(iriBase + "Q24574747");
        Resource resAirport = rdfsModel.createResource(iriBase + "Q1248784");
	}
	
	private void addProperties() {
		String propertyBase = "https://www.wikidata.org/wiki/Property:";
		Property propCapital = rdfsModel.createProperty(propertyBase + "P36", "capital");
		Property propContinent = rdfsModel.createProperty(propertyBase + "P30", "continent");
		Property propHighest = rdfsModel.createProperty(propertyBase + "P610", "highest");
		Property propAltitude = rdfsModel.createProperty(propertyBase + "P2044", "altitude");
		Property propPopulation = rdfsModel.createProperty(propertyBase + "P1082", "population");
		Property propArea = rdfsModel.createProperty(propertyBase + "P2046", "area");
		Property propGovernment = rdfsModel.createProperty(propertyBase + "P122", "government");
		Property propLifeExpectancy = rdfsModel.createProperty(propertyBase + "P2250", "life expectancy");
		Property propLocatedNextToWater = rdfsModel.createProperty(propertyBase + "P206", "located next to water");
		Property propHeadOfState = rdfsModel.createProperty(propertyBase + "P35", "head of state");
		Property propSharesBoarderWith = rdfsModel.createProperty(propertyBase + "P47", "shares boarder with");
		Property propWaterPercent = rdfsModel.createProperty(propertyBase + "P2927", "water percent");
		Property propGDP = rdfsModel.createProperty(propertyBase + "P2132", "GDP");
		Property propIncome = rdfsModel.createProperty(propertyBase + "P3529", "income");
		Property propInception = rdfsModel.createProperty(propertyBase + "P571", "inception");
		
		// add the domain country to properties
		propCapital.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propContinent.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propHighest.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propAltitude.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propPopulation.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propLifeExpectancy.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propArea.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propGovernment.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propHeadOfState.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propSharesBoarderWith.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propGDP.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propIncome.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propLocatedNextToWater.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		propWaterPercent.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q6256"));
		
		// add the domain capital to properties
		propPopulation.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q5119"));
		propAltitude.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q5119"));
		propLocatedNextToWater.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q5119"));
		propInception.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q5119"));
		propInception.addProperty(RDFS.domain, rdfsModel.getResource(iriBase + "Q5119"));
	}
	
	
}
