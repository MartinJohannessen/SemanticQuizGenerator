package semanticQuizGenerator;

import java.util.Scanner;

public class TerminalQuiz {
	String country = "http://www.wikidata.org/entity/Q20";
	
	
	
	public TerminalQuiz(String countryIRI) {
		this.country = countryIRI;
		
		
		
        // Using Scanner for Getting Input from User 
        Scanner in = new Scanner(System.in); 
  
        String s = in.nextLine(); 
        System.out.println("You entered string "+s); 
  
        int a = in.nextInt(); 
        System.out.println("You entered integer "+a); 
  
        float b = in.nextFloat(); 
        System.out.println("You entered float "+b); 
		
	}

}
