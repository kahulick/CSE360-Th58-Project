package effortLoggerV2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
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
import EffortLogger.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@SuppressWarnings("unused")


public class PlanningPokerToolController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	Label roundLabel = new Label();
	@FXML
	TextField projectTypeInput = new TextField();
	@FXML
	TextField projectNameInput = new TextField();
	@FXML
	TextField keyWordsInput = new TextField();
	@FXML
	Button storyPointsButton = new Button();
	
	
	
	public void launching() {
		System.out.println("PLANNING POKER TOOL");
//		Prototype1 proto1 = new Prototype1();
//		proto1.testingAgain();
	}
	
	public void exitPlanningPokerTool(ActionEvent event) throws IOException {
		System.out.println("leaving.");
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void loadRelevantLogs(ActionEvent event) {
		System.out.println("Here are your logs");
		storyPointsButton.setVisible(true);
	}
	
	public void endPlanningPokerRound (ActionEvent event) {
		System.out.println("You've ended this round.");
	}
	
	public void calculateStoryPoints(ActionEvent event) {
		System.out.println("STORY POINTS???");
	}
//
//	@SuppressWarnings("null")
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		ObservableList<Log> Logs = FXCollections.observableArrayList();
//		// can't figure out adding it yet
//		// TODO Auto-generated method stub
//		Log log1 = new Log("testProject", 1, "10/29/23", "10.25.03", "Planning", "buisiness", "need to make UI");
//		Log log2 = new Log("testProject", 2, "10/24/23", "12.36.03", "Planning2", "buisiness", "need to make UI");
//		Logs.add(log1);
//		Logs.addAll(log1, log2);
//		tableView.setItems(Logs);
//		
//	}
	// (String project, int number, String date, String deltaTime, String lifeCycle, String category, String deliverable) {
	
	
	
	
	
	

}
