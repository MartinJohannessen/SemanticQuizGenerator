package semanticQuizGenerator;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;

public class CreateModel {
	InfModel rdfsModel;
	String iriBase;
	
	public CreateModel() {
		Model rdfModel = ModelFactory.createDefaultModel();
        this.rdfsModel = ModelFactory.createRDFSModel(rdfModel);
        this.iriBase = "http://www.wikidata.org/entity/";
        createModel();
	}
	
	public void createModel() {
        //create resources
        Resource resCountry = rdfsModel.createResource(iriBase + "Country");
        Resource resCapital = rdfsModel.createResource(iriBase + "Capital");
        Resource resContinent = rdfsModel.createResource(iriBase + "Continent");
        Resource resHighestPoint = rdfsModel.createResource(iriBase + "Highest point");
        Resource resAltitude = rdfsModel.createResource(iriBase + "Altitude");
        Resource resPopulation = rdfsModel.createResource(iriBase + "Population");
        Resource resArea = rdfsModel.createResource(iriBase + "Area");
        Resource resGovernment = rdfsModel.createResource(iriBase + "Government");
        Resource resLifeExpectancy = rdfsModel.createResource(iriBase + "Life expectancy");
        Resource resWater = rdfsModel.createResource(iriBase + "Water"); //sea, ocean, lake, rivers
        Resource resHeadOfState = rdfsModel.createResource(iriBase + "Head Of State");
        Resource resSharesBoarderWith = rdfsModel.createResource(iriBase + "Shares boarder with");
        Resource resWaterPercent = rdfsModel.createResource(iriBase + "Water percent");
        Resource resGDP = rdfsModel.createResource(iriBase + "GDP");
        Resource resIncome = rdfsModel.createResource(iriBase + "Income");
        Resource resInception = rdfsModel.createResource(iriBase + "Inception");
        Resource resAirport = rdfsModel.createResource(iriBase + "Airport");
	}
}
