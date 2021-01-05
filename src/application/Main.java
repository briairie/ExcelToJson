package application;

import application.datatier.JsonFileWriter;

/**
 * The Class Main - the entry point for the application
 * 
 * @author Brianna
 * @version August 2020
 *
 */
public class Main {
	/**
	 * Launches application
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		JsonFileWriter writer = new JsonFileWriter();
		writer.createJsonFile();
		
	}
}
