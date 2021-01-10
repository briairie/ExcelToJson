package application.datatier;

import java.io.File;
import java.io.PrintWriter;

import application.model.Converter;

/**
 * Writes JSON file
 * 
 * @author Brianna
 * @version 2020
 */
public class JsonFileWriter {


	/**
	 * Create JSON file from converted string
	 *
	 * @preconditions none
	 * @postconditions none
	 *
	 */
	public void createJsonFile(File file, String content) {
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.print(content);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
