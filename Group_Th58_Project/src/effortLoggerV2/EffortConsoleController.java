package effortLoggerV2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import effortLoggerV2.EffortLogEditorController;
@SuppressWarnings("unused")

public class EffortConsoleController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private EffortLogEditorController effortLogEditor;
	
	public void launchEffortLogEditor(ActionEvent event) throws IOException {
		// effortLogEditor.launchEffortConsole(event);
		
		Parent root = FXMLLoader.load(getClass().getResource("EffortLogEditorUI.fxml")); //initial landing page
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
//	public void launchPlanningPokerTool(ActionEvent event) throws IOException {
//		PlanningPokerToolController newSession = new PlanningPokerToolController();
//		newSession.calculateStoryPoints(event);
//		newSession.launching();
//		
//		Parent root = FXMLLoader.load(getClass().getResource("PlanningPokerToolUI.fxml")); 
//		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//	}
	
	
	public void switchToLanding(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LandingUI.fxml")); //initial landing page
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

