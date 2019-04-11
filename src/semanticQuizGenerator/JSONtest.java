package semanticQuizGenerator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONtest
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("capitals.JSON"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray capitalsList = (JSONArray) obj;
            System.out.println(capitalsList);
             
            //Iterate over employee array
            capitalsList.forEach( cap -> parseCapitalObject( (JSONObject) cap ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings({ "unused" })
	private static void parseCapitalObject(JSONObject cap)
    {
        /**Get capital object within list
        JSONObject capitalObject = (JSONObject) cap.get("capital");
        */
        //Get country name
        String countryName = (String) capitalObject.get("countryLabel");   
        System.out.println(countryName);
         
        //Get capital name
        String capitalName = (String) capitalObject.get("capitalLabel"); 
        System.out.println(capitalName);
         
        //Get population number
        int populationNumber = (int) capitalObject.get("populationLabel");   
        System.out.println(populationNumber);
    }
}