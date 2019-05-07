package semanticQuizGenerator;

import java.io.IOException;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;

import com.fasterxml.jackson.core.JsonGenerationException;

public class TerminalQuiz {
	String country = "http://www.wikidata.org/entity/Q20";
	
	
	
	public TerminalQuiz(String countryIRI) throws JsonGenerationException, IOException {
		this.country = countryIRI;
		
		Model model = new CreateModel().CreateModel();
		Hints hints = new Hints(model, country);
		
		for (String s: hints.hints) {
        	System.out.println(s);
        }
		
		
		
	}

}
