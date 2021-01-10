package application.view;

import java.io.File;

import application.viewmodel.GuiViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ApplicationGuiCodeBehind {
	
	@FXML
    private AnchorPane guiPane;

    @FXML
    private MenuItem fileOpenMenuItem;

    @FXML
    private TextArea jsonTextArea;

    @FXML
    private ComboBox<?> openComboBox;

    @FXML
    private ComboBox<?> saveComboBox;
    
    private GuiViewModel viewModel;
    
    public ApplicationGuiCodeBehind(){
    	this.viewModel = new GuiViewModel();
    }
    
    @FXML
    public void initialize() {
    	this.jsonTextArea.textProperty().bindBidirectional(this.viewModel.getJsonTextProperty());
    }
    
    @FXML
    void handleOpen(ActionEvent event) {
    	Stage stage = (Stage) this.guiPane.getScene().getWindow();
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Excel Sheet");
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Excel Files", "*.xlsx"));
    	File selectedFile = fileChooser.showOpenDialog(stage);
    	if(selectedFile != null) {
    		this.viewModel.openExcelDoc(selectedFile);
    		
    	}
    }

    @FXML
    void handleSave(ActionEvent event) {

    }

}

