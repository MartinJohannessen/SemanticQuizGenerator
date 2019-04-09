package semanticQuizGenerator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jena.ontology.OntModel;
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
        this.iriBase = "http://www.wikidata.org/wiki/";
        createModel();
		OntModel model = ModelFactory.createOntologyModel();
		readOntology("src/ontology/ontology_v3.1.rdf", model);
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
	
	private void readOntology (String file,OntModel model ) {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			model.read(in, "RDF/XML");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
