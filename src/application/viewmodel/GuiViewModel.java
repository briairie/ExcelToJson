package application.viewmodel;

import java.io.File;

import application.model.Converter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GuiViewModel {
	private StringProperty jsonTextProperty;
	private String jsonText;
	
	public GuiViewModel() {
		this.jsonTextProperty = new SimpleStringProperty();
		this.jsonText = "";
	}
	
	public void openExcelDoc(File file) {
		Converter converter = new Converter();
		this.jsonText = converter.convert(file);
		this.displayJsonText();
	}
	
	private void displayJsonText() {
		this.jsonTextProperty.setValue(this.jsonText);
	}
	
	/**
	 * Gets the  jsonTextProperty
	 *
	 * @preconditions none
	 * @postconditions none
	 *
	 * @return the jsonTextProperty
	 */
	public StringProperty getJsonTextProperty() {
		return this.jsonTextProperty;
	}
	
}
