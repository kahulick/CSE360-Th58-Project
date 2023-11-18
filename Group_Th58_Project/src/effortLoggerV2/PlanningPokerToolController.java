package effortLoggerV2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import prototypes.Prototype1;
import prototypes.Prototype2;
import effortLoggerV2.EffortLoggerController;
import EffortLogger.EffortLog;
import EffortLogger.PlanningPokerCalculator;
import EffortLogger.EffortLogsRepository;
import EffortLogger.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.regex.*;


@SuppressWarnings("unused")


public class PlanningPokerToolController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// public ObservableList<String> keyWords = FXCollections.observableArrayList();  // relevant later
	// public List<String> keyWords = new ArrayList<String>();
	public String projectType;
	public String projectName;
	public String keyWords;
	public int roundCounter;
	
	private EffortLogsRepository effortLogsRepository = new EffortLogsRepository();
	private PlanningPokerCalculator planningPokerCalculator = new PlanningPokerCalculator();
	
	private ObservableList<EffortLog> historicalData = FXCollections.observableArrayList(); // holds all Effort Logs from history
	private ObservableList<String> displayedData = FXCollections.observableArrayList();		// holds all string Logs from history
	private ObservableList<String> refinedData;	// holds all string Logs from refined search 
	private ObservableList<EffortLog> refinedLogData; // holds all Effort Logs from refined search
	
	private ObservableList<Double> logEstimates;
	
	@FXML
	Label roundLabel = new Label();
	@FXML
	TextField projectTypeInput = new TextField();
	@FXML
	TextField projectNameInput = new TextField();
	@FXML
	TextField keyWordsInput = new TextField();
	@FXML
	Label promptLabel;
	@FXML
	Button loadHistory;
	@FXML
	Label estStoryPoints = new Label();
	
	
	@FXML
	Button storyPointsButton = new Button();
	@FXML
	Button updateSearchButton = new Button();
	@FXML
	Label currentKeyWords;
	@FXML
	Label projectTypeLabel;
	@FXML
	Label projectNameLabel;
	@FXML
	Label logHistoryLabel;
	@FXML
	Button submit = new Button();
	@FXML
	Button adjustWeight = new Button();
	@FXML
	private ListView<String> userEffortLogs;
	@FXML
	private ListView<Double> individualLogEffort;
	

	
	
	@FXML
	public void initialize() {		// allows program to keep track of the selected log
		userEffortLogs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String selectedLog) {
				System.out.println("Selected: " + selectedLog);
				
				if (individualLogEffort.isVisible() == true) {
					individualLogEffort.getSelectionModel().select(userEffortLogs.getSelectionModel().getSelectedIndex());
				}
			
			}
		});
		roundCounter = 1;
		roundLabel.setText(Integer.toString(roundCounter));
//		if (individualLogEffort.isVisible() == true) {
//			
//		}
	}
	
	public void exitPlanningPokerTool(ActionEvent event) throws IOException {
		System.out.println("leaving.");
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void loadRelevantLogs(ActionEvent event) throws IOException {
		int count = effortLogsRepository.getEffortLogs();
		EffortLog[] effortLogs = effortLogsRepository.getEffortRepo(count);
		for (EffortLog log : effortLogs) {
			historicalData.add(log); 				// updates array of logs
			displayedData.add(mapToString(log));	// updates array of strings (that represent logs)
		}
		userEffortLogs.setItems(displayedData);
		userEffortLogs.setVisible(true);
		
		projectType = projectTypeInput.getText();
		projectName = projectNameInput.getText();
		keyWords = keyWordsInput.getText();
		currentKeyWords.setText("Key Words: " + keyWords);
		projectTypeLabel.setText("Project Type: " + projectType);
		projectNameLabel.setText("Project Name: " + projectName);
		
		projectTypeInput.setVisible(false);
		projectNameInput.setVisible(false);
		keyWordsInput.setVisible(false);
		promptLabel.setVisible(false);
		loadHistory.setVisible(false);
		
		logHistoryLabel.setVisible(true);
		storyPointsButton.setVisible(true);
		updateSearchButton.setVisible(true);
	}
	
	
	public void updateInput(ActionEvent event) throws IOException {
		submit.setVisible(true);
		keyWordsInput.setLayoutY(60);
		keyWordsInput.setVisible(true);
		individualLogEffort.setVisible(false);
		estStoryPoints.setVisible(false);
	}
	
	
	public void submitUpdates(ActionEvent event) throws IOException {
		refinedLogData = FXCollections.observableArrayList();
		keyWords = keyWordsInput.getText();
		currentKeyWords.setText("Key Words: " + keyWords);
		projectTypeLabel.setText("Project Type: " + projectType);
		projectNameLabel.setText("Project Name: " + projectName);
		submit.setVisible(false);
		keyWordsInput.setVisible(false);
		refineData(keyWords);
		userEffortLogs.setItems(refinedData);
		for (EffortLog log: historicalData) {	// refined list of log objects 
			if (refinedData.contains(mapToString(log))) {
				refinedLogData.add(log);
			}
		}
	}
	
	public void endPlanningPokerRound (ActionEvent event) {
		roundCounter++;
		roundLabel.setText(Integer.toString(roundCounter));
		System.out.println("You've ended this round.");
	}
	
	public void calculateStoryPoints(ActionEvent event) {
		logEstimates = planningPokerCalculator.calculateIndividualEffort(refinedLogData, historicalData);
		double storyPoints = planningPokerCalculator.calculateStoryPoints(logEstimates);
		if (roundCounter > 1) {
			adjustWeight.setVisible(true);
		}
		//adjustWeight.setVisible(true);
		individualLogEffort.setItems(logEstimates);
		individualLogEffort.setVisible(true);	// table next to it to hold the individual calculations for each effort log
		estStoryPoints.setText("Estimated Story Points: " + storyPoints);
		estStoryPoints.setVisible(true);
	}
	
	public String mapToString(EffortLog log) {	// refactored from Kevin's repository class
		//DateTimeFormatter 
	    DateTimeFormatter makeDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        String date = makeDate.format(log.getDate());
        
        //change startTime to string
        DateTimeFormatter makeTime = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
        String startTime = makeTime.format(log.getStartTime());
        String stopTime = makeTime.format(log.getStopTime());
        
        String strLog = log.getProjectType() + "," +
	    		date + "," +
	    		startTime + "," + 
	    		stopTime + "," +
	    		log.getDeltaTime() + "," +
	    		log.getLifeCycleStep() + "," +
	    		log.getEffortCategory() + "," +
	    		log.getEffortCategoryItem();
        return strLog;
	}
	
	public void refineData(String keywords) {
		refinedData = FXCollections.observableArrayList();
		if (!keyWords.equals("")) {
			keyWords = keyWords.replaceAll("\s","");
			String[] kw = keywords.split("[, ]", 0);
			for (String log: displayedData) {
				for (String keyWord: kw) {
					if (keyWord.equals("") == false && log.toLowerCase().contains(keyWord.toLowerCase()) && refinedData.contains(log) == false) {
						refinedData.add(log);
					}
				}
			}
		} else {
			for (String strLog: displayedData) {	// if no keywords given, load all log history
				refinedData.add(strLog);
			}
		}
	}
	
	public void weightedStoryPoints(ActionEvent event) {
		userEffortLogs.setLayoutX(23); //-> 39 or 23
		individualLogEffort.setLayoutX(786);
		System.out.println("Insert Weighted StoryPoints");
	}
	
	
	// test to make sure one object retains the original historical data
	// and the other object contains the updated logs
	public void verificationTest() {		
		
		System.out.println("LOG HISTORY:");
		for (String strLog: displayedData) {
			System.out.println(strLog);
		}
		
		System.out.println("\nREFINED LOGS: ");
		for (String strLog: refinedData) {
			System.out.println(strLog);
		}
	}
	

	


}
