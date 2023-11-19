package effortLoggerV2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import prototypes.Prototype1;
import prototypes.*;
import effortLoggerV2.PlanningPokerToolController;

@SuppressWarnings("unused")


/**
 * @author Kaelyn Hulick 
 * 
 */


// DEPRECATED

public class EffortLoggerController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Tab effortConsoleTab = new Tab();
	@FXML
	private Tab logEditorTab = new Tab();
	@FXML
	private Tab defectConsoleTab = new Tab();
	@FXML
	private Tab logsTab = new Tab();
	@FXML
	private Tab definitionsTab = new Tab();
	@FXML
	private Tab managerTab = new Tab();
	
	
	@FXML
	public void change(Event event) throws IOException {
		
		if (effortConsoleTab.isSelected()) {
			System.out.println("EFFORT CONSOLE");
		}
	
		if (logEditorTab.isSelected()) {
			System.out.println("EDIT");
		}
		
		if (defectConsoleTab.isSelected()) {
			System.out.println("DEFECT");
		}
		
		if (logsTab.isSelected()) {
			System.out.println("LOGS");
		}
		
		if (definitionsTab.isSelected()) {
			System.out.println("DEF");
		}
		
		if (managerTab.isSelected()) {
			System.out.println("MANAGE");
		}
		
	}
	
	public void launchPlanningPokerTool(ActionEvent event) throws IOException {
		PlanningPokerToolController newSession = new PlanningPokerToolController();
//		newSession.calculateStoryPoints(event);
		// newSession.launching();
		
		Parent root = FXMLLoader.load(getClass().getResource("PlanningPokerToolUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void prototype2(ActionEvent event) throws IOException {
		Prototype2 proto2 = new Prototype2();
		proto2.launch();
//		System.out.println("LAUNCH");
	}

	
}