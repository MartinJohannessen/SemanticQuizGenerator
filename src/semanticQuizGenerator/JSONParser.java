package semanticQuizGenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONParser {

	/**
     * Parses a JSON file into string format
     * 
     * @param path to a JSON file
     * @return jsonString
     */
	public static String readJson(String path) {
		StringBuilder json = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {

			// read line by line
			String line;
			while ((line = br.readLine()) != null) {
				json.append(line).append("\n");
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		String jsonString = json.toString();

		return jsonString;
	}
}