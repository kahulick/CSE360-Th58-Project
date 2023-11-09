package effortLoggerV2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import effortLoggerV2.EffortLogEditorController;
import EffortLogger.Definitions;
@SuppressWarnings("unused")

public class EffortConsoleController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private EffortLogEditorController effortLogEditor;
	private Definitions definitions = new Definitions();
	private boolean start = false;
	
	@FXML
	private ComboBox<String> projectItems = new ComboBox<String>();
	@FXML
	private ComboBox<String> lifeCycleItems = new ComboBox<String>(); 
	
	
	// screen size 1200x 800y
	
	public void launchEffortLogEditor(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortLogEditorUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void launchDefectConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("DefectConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void launchLogs(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogsUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void startAnActivity(ActionEvent event) {
		start = true;
		System.out.println("Clock Started");
	}
	
	public void stopActivity(ActionEvent event) {
		if (start == true) {
			System.out.println("Clock stopped");
		}
		start = false;
	}
	
	@FXML
	public void initialize() {
		initializeProjectItems();
		initializeLifeCycleItems();

	}
	
	
	@FXML
	public void initializeProjectItems() {
		if (projectItems.getValue() == null) {
			projectItems.setItems(definitions.options1a);
		}
		System.out.println(projectItems.getValue());
	}
	
	@FXML
	public void initializeLifeCycleItems() {
		lifeCycleItems.getItems().removeAll();
		if (projectItems.getValue() == "Business Project") {
			lifeCycleItems.setItems(definitions.options1a1);
		} 
		if (projectItems.getValue() == "Development Project") {
			lifeCycleItems.setItems(definitions.options1a2);
		}
	}
	
	

}

