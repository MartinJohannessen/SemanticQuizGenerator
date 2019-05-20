package semanticQuizGenerator;

import java.io.IOException;
import java.io.StringReader;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.github.jsonldjava.utils.JsonUtils;

public class CreateModel {


	public Model CreateModel() throws JsonGenerationException, IOException {

		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		String countriesJSON = JSONParser.readJson("src/data/countries.json");
		String countriesRankedByAreaJSON = JSONParser.readJson("src/data/countriesRankedByArea.json");
		String allJSON = countriesJSON.substring(0, countriesJSON.length()-2)+", "+capitalsJSON.substring(1);

		CreateContextObject createContext = new CreateContextObject();
		Object jsonObj = null;
		try {
			jsonObj = JsonUtils.fromString(allJSON);
		} catch (IOException e) {
			e.printStackTrace();}


		Object expandedObj = ExpandJSON.ExpandJSON(createContext.contextObj, jsonObj);

		String jsonStr = JsonUtils.toPrettyString(expandedObj);
		
	    Model model = ModelFactory.createDefaultModel();
	    RDFDataMgr.read(model, new StringReader(jsonStr), "", Lang.JSONLD);
	    model.write(System.out, "TURTLE");

		return model;

	}
}
