package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Class Main - the entry point for the application
 * 
 * @author Brianna
 * @version August 2020
 *
 */
public class Main extends Application {
	private static final String GUI_FXML = "view/ApplicationGui.fxml";
	private static final String WINDOW_TITLE = "Excel to Json";

	/**
	 * Launches application
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(GUI_FXML));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setTitle(WINDOW_TITLE);
			stage.show();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
	}
}
