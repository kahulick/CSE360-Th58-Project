package effortLoggerV2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import effortLoggerV2.EffortLoggerController;
import EffortLogger.EffortLog;
import EffortLogger.PlanningPokerCalculator;
import EffortLogger.EffortLogsRepository;
import EffortLogger.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.regex.*;
@SuppressWarnings("unused")


/**
 * @author Kaelyn Hulick 
 * 
 * 
 * Title: PlanningPokerToolController Class
 * 
 * Description: A class that controls the user input in the PlanningPokerTool UI. It makes calls to the EffortLoggerRepository class 
 * to import data and refine the data. This class also controls which Effort Log objects are exported to the
 * PlanningPokerToolCalculator class and the returned data is displayed to the user. 
 * 
 * 
 */


public class PlanningPokerToolController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
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
	private ObservableList<Integer> logWeights;
	
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
	TextInputDialog weight = new TextInputDialog();
	
	
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
	private ListView<Integer> individualLogWeight;
	

	
	// initialize the UI to allow program to keep track of user selection from the effort log list and initializes the round
	@FXML
	public void initialize() {		
		userEffortLogs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String selectedLog) {
				System.out.println("Selected: " + selectedLog);
				
				if (individualLogEffort.isVisible() == true) {
					individualLogEffort.getSelectionModel().select(userEffortLogs.getSelectionModel().getSelectedIndex());
				}
				if (individualLogWeight.isVisible() == true) {
					individualLogWeight.getSelectionModel().select(userEffortLogs.getSelectionModel().getSelectedIndex());
				}
			
			}
		});
		roundCounter = 1;
		roundLabel.setText(Integer.toString(roundCounter));
	}
	
	// launches the effort console UI
	public void exitPlanningPokerTool(ActionEvent event) throws IOException {
		System.out.println("leaving.");
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// loads the users entire Effort Log repository before refining the search
	// takes user input for project type, project name, and keywords
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
	
	// Allows the user to update their search by inputting new keywords or removing them
	public void updateInput(ActionEvent event) throws IOException {
		submit.setVisible(true);
		keyWordsInput.setLayoutY(60);
		keyWordsInput.setVisible(true);
		individualLogEffort.setVisible(false);
		individualLogWeight.setVisible(false);
		estStoryPoints.setVisible(false);
		adjustWeight.setVisible(false);
	}
	
	// submits the user inputed keywords, refines the presented log data, and displays that to the user
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
	
	// ends the planning poker round and increments the round counter label 
	public void endPlanningPokerRound (ActionEvent event) {
		roundCounter++;
		roundLabel.setText(Integer.toString(roundCounter));
		System.out.println("You've ended this round.");
	}
	
	// calls the planning poker calculator to get the individual estimates and calculate the final estimated
	// story point value. It also displays the weight if the user is participating in subsequent rounds
	public void calculateStoryPoints(ActionEvent event) {
		logEstimates = planningPokerCalculator.calculateIndividualEffort(refinedLogData, historicalData);
		double storyPoints = planningPokerCalculator.calculateStoryPoints(logEstimates);
		if (roundCounter > 1) {
			adjustWeight.setVisible(true);
			logWeights = FXCollections.observableArrayList();
			userEffortLogs.setLayoutX(23); //-> 39 or 23
			adjustWeight.setLayoutX(23);
			individualLogEffort.setLayoutX(829); // gonna be on the far right
			if (refinedLogData != null) {
				for (EffortLog log: refinedLogData) {
					logWeights.add(3);
				}
			} else {
				for (EffortLog log: historicalData) {
					logWeights.add(3);
				}
			}

			individualLogWeight.setItems(logWeights);
			individualLogWeight.setVisible(true);
		}
		//adjustWeight.setVisible(true);
		individualLogEffort.setItems(logEstimates);
		individualLogEffort.setVisible(true);	// table next to it to hold the individual calculations for each effort log
		estStoryPoints.setText("Estimated Story Points: " + storyPoints);
		estStoryPoints.setVisible(true);
	}
	
	// maps all Effort Log objects to a string. This string is used to display in the UI and used during the search tool
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
	
	// Search functionality: refines the  presented Effort Logs displayed to the user based off their keywords
	// if no keywords have been entered, the users entire effort log repository is returned
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
	
	// Allows the user to select a log to adjust its weight during subsequent rounds
	// uses the selected log to adjust weight & recalculate both individual and overall story point estimate
	public void weightedStoryPoints(ActionEvent event) {
		System.out.println("Insert Weighted StoryPoints");
		weight.setHeaderText("Select a weight 1-5");
		weight.setContentText("Weight:");
		if (userEffortLogs.getSelectionModel().getSelectedItem() != null) {
			Optional<String> result = weight.showAndWait();		
			result.ifPresent(w -> {								 
				System.out.println(w);
				if (Integer.parseInt(w) > 5) {
					w = "5";
				}
				try {
					logWeights.set(userEffortLogs.getSelectionModel().getSelectedIndex(), Integer.parseInt(w));
					if (refinedLogData != null) {
						EffortLog selectedLog = refinedLogData.get(userEffortLogs.getSelectionModel().getSelectedIndex());
						calculateWeightedValue(Integer.parseInt(w), selectedLog, userEffortLogs.getSelectionModel().getSelectedIndex());
					} else {
						EffortLog selectedLog = historicalData.get(userEffortLogs.getSelectionModel().getSelectedIndex());
						calculateWeightedValue(Integer.parseInt(w), selectedLog, userEffortLogs.getSelectionModel().getSelectedIndex());
					}
				} catch(Exception e) { 
					System.out.println("No log selected.");
				}

			});
		}
		generateWeightedStoryPoints();
	}
	
	// update the EffortLogs estimated value & store it back in the list
	public void calculateWeightedValue(int weight, EffortLog log, int index) {
		double logWeight = planningPokerCalculator.calculateWeightedEffort(weight, log);
		System.out.println(logEstimates.get(userEffortLogs.getSelectionModel().getSelectedIndex()));
		System.out.println(logWeight);
		logEstimates.set(index, logWeight);
		individualLogEffort.setItems(logEstimates);
	}
	
	// calculate the new story point estimate using the new weighted log values
	public void generateWeightedStoryPoints() {
		double storyPoints = planningPokerCalculator.calculateStoryPoints(logEstimates);
		System.out.println(storyPoints);
		estStoryPoints.setText("Estimated Story Points: " + storyPoints);
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
