package effortLoggerV2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import prototypes.Prototype1;
import prototypes.Prototype2;
import effortLoggerV2.EffortLoggerController;
import EffortLogger.EffortLog;
import EffortLogger.PlanningPokerCalculator;
import EffortLogger.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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


	
	public void launching() {
		System.out.println("PLANNING POKER TOOL");
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
		projectType = projectTypeInput.getText();
		projectName = projectNameInput.getText();
		keyWords = keyWordsInput.getText();
		System.out.println("Current Project: " + projectType + ": " + projectName);
		System.out.println("Here are your logs for: " + keyWords);
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
		keyWordsInput.setLayoutY(64);
		keyWordsInput.setVisible(true);
	}
	
	public void submitUpdates(ActionEvent event) throws IOException {
		keyWords = keyWordsInput.getText();
		currentKeyWords.setText("Key Words: " + keyWords);
		currentKeyWords.setText("Key Words: " + keyWords);
		projectTypeLabel.setText("Project Type: " + projectType);
		projectNameLabel.setText("Project Name: " + projectName);
		submit.setVisible(false);
		keyWordsInput.setVisible(false);
	}
	
	
	
	public void endPlanningPokerRound (ActionEvent event) {
		System.out.println("You've ended this round.");
	}
	
	public void calculateStoryPoints(ActionEvent event) {
		System.out.println("STORY POINTS???");
	}


}
