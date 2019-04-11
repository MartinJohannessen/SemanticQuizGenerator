package semanticQuizGenerator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;

public class CreateModel {
	InfModel rdfsModel;
	String iriBase;
	OntModel ontology;
	
	public CreateModel() {
		Model rdfModel = ModelFactory.createDefaultModel();
        this.rdfsModel = ModelFactory.createRDFSModel(rdfModel);
        this.iriBase = "http://www.wikidata.org/wiki/";
        createModel();
        ontology = ModelFactory.createOntologyModel();     
		readOntology("src/ontology/ontology_v3.1.rdf", ontology);
		String ontologyIribase = "http://sws.geonames.org/";
		addProperties();
	}
	
	public void createModel() {
        //create resources
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
	
	private void readOntology (String file, OntModel model ) {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			model.read(in, "RDF/XML");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
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
		Property propWater = rdfsModel.createProperty(propertyBase + "P206", "water");
		Property propHeadOfState = rdfsModel.createProperty(propertyBase + "P35", "head of state");
		Property propSharesBoarderWith = rdfsModel.createProperty(propertyBase + "P47", "shares boarder with");
		Property propWaterPercent = rdfsModel.createProperty(propertyBase + "P2927", "water percent");
		Property propGDP = rdfsModel.createProperty(propertyBase + "P2132", "GDP");
		Property propIncome = rdfsModel.createProperty(propertyBase + "P3529", "income");
		Property propInception = rdfsModel.createProperty(propertyBase + "P571", "inception");
	}
}
