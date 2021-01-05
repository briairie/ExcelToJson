package application.datatier;

import java.io.PrintWriter;

import application.model.Converter;

/**
 * Writes JSON file
 * 
 * @author Brianna
 * @version 2020
 */
public class JsonFileWriter {

	private static final String FILE_NAME = "jsonExampleFile.json";

	/**
	 * Create JSON file from converted string
	 *
	 * @preconditions none
	 * @postconditions none
	 *
	 */
	public void createJsonFile() {
		try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
			Converter converter = new Converter();
			writer.print(converter.convert());
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
