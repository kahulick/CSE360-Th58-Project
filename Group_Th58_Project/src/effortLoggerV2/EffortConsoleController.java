package effortLoggerV2;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import effortLoggerV2.EffortLogEditorController;
import effortLoggerV2.LogsController;
import EffortLogger.Definitions;
import EffortLogger.EffortLog;
import EffortLogger.EffortLogsRepository;

//added imports for txt file reading
//not all are needed, will prob trim later
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@SuppressWarnings("unused")

/**
 * @author Kaelyn Hulick 
 * 
 * Title: EfforConsoleController Class 
 * 
 * Description: A class that controls the user input and UI for the Effort Console. The user input is then mapped to an Effort Log 
 * object that can be stored and used for necessary calculations.  
 * 
 */



public class EffortConsoleController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private EffortLog effortLog;
	private Definitions definitions = new Definitions();
	private LogsController logsController = new LogsController();
	private PlanningPokerToolController planningPokerToolController = new PlanningPokerToolController();
	private EffortLogsRepository effortLogsRepository = new EffortLogsRepository();
	private DefectConsoleController defectConsoleController = new DefectConsoleController();
	private LocalDate date; 
	private LocalTime startTime;
	private LocalTime stopTime;
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

	
	// launches the effort log editor 
	public void launchEffortLogEditor(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortLogEditorUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// launches the defect console
	public void launchDefectConsole(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("DefectConsoleUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DefectConsoleUI.fxml"));
		loader.setController(defectConsoleController);
		defectConsoleController = loader.<DefectConsoleController>getController();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// launches the logs UI --> exports data between controllers 
	public void launchLogs(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogsUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LogsUI.fxml"));
		loader.setController(logsController);
		logsController = loader.<LogsController>getController();
		logsController.setEffortLog(effortLog);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// launches the planning poker tool UI --> exports data between controllers
	public void launchPlanningPokerTool(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("PlanningPokerToolUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanningPokerToolUI.fxml"));
		loader.setController(planningPokerToolController);
		planningPokerToolController = loader.<PlanningPokerToolController>getController();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	// ALL FOLLOWING METHODS: components for retrieving user data
	
	// initializes the timer and stores the start time when the button is clicked
	public void startAnActivity(ActionEvent event) { 
		start = true;
		clockLabel.setText("Clock is Running");
		clockLabel.setStyle("-fx-background-color: green;");
		startTime = LocalTime.now().withNano(0);
		date = LocalDate.now();
	}
	
	// ends the timer and stores the stop time when the button is clicked
	public void stopActivity(ActionEvent event) {	
		if (start == true) {
			clockLabel.setText("Clock is Stopped");
			clockLabel.setStyle("-fx-background-color: red;");
			stopTime = LocalTime.now().withNano(0);
			logEffort();
		}
		start = false;
	}
	
	// initializes all drop down menu items
	@FXML
	public void initialize() {
		initializeProjectItems();
		initializeLifeCycleItems();
		initializeEffortCategory();
		initializeEffortCategoryItems();
		// System.out.println(getRandomNumberString());
	}
	
	// initializes drop down menu for the project type
	@FXML
	public void initializeProjectItems() {
		if (projectItems.getValue() == null) {
			projectItems.setItems(definitions.options1a);
		}
	}
	
	// initializes drop down menu for the life cycle step, based on the project type
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
	
	// initializes the drop down menu for the effort category
	@FXML
	public void initializeEffortCategory() {
		if (effortCategories.getValue() == null) {
			effortCategories.setItems(definitions.options3);
		}
	}
	
	// initializes the drop down menu for the effort category items, based on the selected effort category
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
			otherDetails.setVisible(true);	// hide the text if Other is not selected
		}
		effortCategoryLabel.setText(effortCategories.getValue());
	}
	
	@FXML	// creates the effortLog object to save after completing an activity
	public EffortLog logEffort() {
		String effortDetails;
		if (otherDetails.isVisible() == true) {
			effortDetails = otherDetails.getText();
		} else {
			effortDetails = effortCategoryItems.getValue(); // if "other" -> comes from text field
		}
		effortLog = new EffortLog(projectItems.getValue(), date, startTime, stopTime, lifeCycleItems.getValue(), effortCategories.getValue(), effortDetails);
		effortLogsRepository.CreateEF(effortLog);	// saves new log in the text file
		return effortLog;
	}

}

