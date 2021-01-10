package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Converts Excel documents to Json
 * 
 * @author Brianna
 * @version 2020
 */
public class Converter {
	private String convertedString = "";

	/**
	 * Converts Excel file into a json file
	 *
	 * @preconditions none
	 * @postconditions none
	 * 
	 * @return converted string
	 */
	public String convert(File file) {
		try (FileInputStream excelFile = new FileInputStream(file);
				Workbook workbook = new XSSFWorkbook(excelFile);) {
			Iterator<Sheet> sheetIterator = workbook.iterator();
			this.convertedString = ""; 
			this.convertedString += "[";
			while (sheetIterator.hasNext()) {
				this.printSheet(sheetIterator);
			}
			this.convertedString += "]";
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return this.convertedString;
	}

	private void printSheet(Iterator<Sheet> sheetIterator) {
		Sheet sheet = sheetIterator.next();
		ArrayList<String> properties = new ArrayList<String>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			if (currentRow.getRowNum() == 0) {
				properties = this.getProperties(currentRow);
				continue;
			}
			this.printObject(currentRow, properties, rowIterator.hasNext());
		}

	}

	private void printObject(Row row, ArrayList<String> properties, boolean hasNext) {
		Iterator<Cell> cellIterator = row.iterator();
		String object = "{" + System.lineSeparator();
		while (cellIterator.hasNext()) {
			Cell currentCell = cellIterator.next();
			int index = currentCell.getColumnIndex();
			String property = properties.get(index);
			object += this.getObjectString(currentCell, property);
			if (cellIterator.hasNext()) {
				object += ",";
			}
			object += System.lineSeparator();
		}
		object += "}";
		if (hasNext) {
			object += "," + System.lineSeparator();
		}
		this.convertedString += object;
	}

	private String getObjectString(Cell currentCell, String property) {
		String object = "\t";
		if (currentCell.getCellType() == CellType.STRING) {
			String value = currentCell.getStringCellValue();
			object += this.getPropertyString(property, value);
		} else if (currentCell.getCellType() == CellType.NUMERIC) {
			Double value = currentCell.getNumericCellValue();
			object += this.getPropertyString(property, value);
		} else if (currentCell.getCellType() == CellType.BOOLEAN) {
			Boolean value = currentCell.getBooleanCellValue();
			object += this.getPropertyString(property, value);
		} else {
			System.out.println(currentCell.getCellType() + " is not supported");
		}
		return object;
	}

	private String getPropertyString(String property, String value) {
		return "\"" + property + "\": " + "\"" + value + "\"";
	}

	private String getPropertyString(String property, double value) {
		return "\"" + property + "\": " + value;
	}

	private String getPropertyString(String property, boolean value) {
		return "\"" + property + "\": " + value;
	}

	private ArrayList<String> getProperties(Row currentRow) {
		ArrayList<String> properties = new ArrayList<String>();
		Iterator<Cell> cellIterator = currentRow.iterator();
		while (cellIterator.hasNext()) {
			Cell currentCell = cellIterator.next();
			if (currentCell.getCellType() != CellType.STRING) {
				System.err.println(
						currentCell.getRowIndex() + ", " + currentCell.getColumnIndex() + " is not CellType.String");
			}
			String property = currentCell.getStringCellValue().toLowerCase();
			properties.add(property);
		}
		return properties;
	}
}
