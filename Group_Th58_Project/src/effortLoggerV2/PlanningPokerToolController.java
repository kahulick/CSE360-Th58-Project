package effortLoggerV2;

import javafx.application.Application;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import prototypes.Prototype1;
import effortLoggerV2.EffortLoggerController;
import EffortLogger.*;

@SuppressWarnings("unused")


public class PlanningPokerToolController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField txtfield1;
	
	@FXML 
	private TableView<Log> table;
	
	
	public void launching() {
		System.out.println("PLANNING POKER TOOL");
		Prototype1 proto1 = new Prototype1();
		proto1.testingAgain();
	}
	
	public void exitPlanningPokerTool(ActionEvent event) throws IOException {
		System.out.println("leaving.");
		Parent root = FXMLLoader.load(getClass().getResource("EffortLoggerUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void calculateStoryPoints(ActionEvent event) throws IOException {
		System.out.println("STORY POINTS???");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
