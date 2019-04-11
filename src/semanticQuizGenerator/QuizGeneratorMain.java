package semanticQuizGenerator;


public class QuizGeneratorMain {
	
	public static void main(String[] args) {
	
		CreateModel model = new CreateModel();
		
		String capitalsJSON = JSONParser.readJson("src/data/capitals.json");
		System.out.println(capitalsJSON);
		
		
		
	}
}

