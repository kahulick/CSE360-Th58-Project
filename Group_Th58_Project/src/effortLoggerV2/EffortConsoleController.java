package effortLoggerV2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
//import javafx.scene.control.Label.setBackground;
import javafx.scene.control.TextField;
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
	private String date; 
	private String startTime;
	private String stopTime;
	private boolean start = false;
	
	@FXML
	private ComboBox<String> projectItems = new ComboBox<String>();
	@FXML
	private ComboBox<String> lifeCycleItems = new ComboBox<String>(); 
	@FXML
	private ComboBox<String> effortCategories = new ComboBox<String>();
	@FXML
	private ComboBox<String> effortCategoryItems = new ComboBox<String>();
	@FXML
	private Label effortCategoryLabel = new Label();
	@FXML
	private Label otherDetailsLabel = new Label();
	@FXML
	private TextField otherDetails = new TextField();
	@FXML
	private Label clockLabel = new Label();

	
	
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
	
	public void startAnActivity(ActionEvent event) { // calculate delta time within the Log class
		start = true;
		clockLabel.setText("Clock is Running");
		clockLabel.setStyle("-fx-background-color: green;");
		startTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		date = startTime.substring(0,8);
	}
	
	public void stopActivity(ActionEvent event) {	// calculate delta time within the Log class
		if (start == true) {
			clockLabel.setText("Clock is Stopped");
			clockLabel.setStyle("-fx-background-color: red;");
			stopTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		}
		start = false;
	}
	
	@FXML
	public void initialize() {
		initializeProjectItems();
		initializeLifeCycleItems();
		initializeEffortCategory();
		initializeEffortCategoryItems();
	}
	
	@FXML
	public void initializeProjectItems() {
		if (projectItems.getValue() == null) {
			projectItems.setItems(definitions.options1a);
		}
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
	
	@FXML
	public void initializeEffortCategory() {
		if (effortCategories.getValue() == null) {
			effortCategories.setItems(definitions.options3);
		}
	}
	
	@FXML
	public void initializeEffortCategoryItems() {
		effortCategoryItems.getItems().removeAll();
		if (effortCategories.getValue() == "Plans") {
			effortCategoryItems.setItems(definitions.options3a);
		}
		if (effortCategories.getValue() == "Deliverables") {
			effortCategoryItems.setItems(definitions.options3b);
		}
		if (effortCategories.getValue() == "Interruptions") {
			effortCategoryItems.setItems(definitions.options3c);
		}
		if (effortCategories.getValue() == "Defects") {
			effortCategoryItems.setItems(definitions.options3d);
		}
		if (effortCategories.getValue() == "Others") {
			effortCategoryItems.setItems(definitions.options3e);  // empty "" in array list
			otherDetailsLabel.setVisible(true);
			otherDetails.setVisible(true);
		}
		effortCategoryLabel.setText(effortCategories.getValue());
	}
	

	

}

