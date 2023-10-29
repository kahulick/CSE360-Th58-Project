package userInterfaceJavaFX;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
@SuppressWarnings("unused")

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void switchToLanding(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LandingUI.fxml")); //initial landing page
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPrototype1(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Prototype1UI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPrototype2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Prototype2UI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPrototype3(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Prototype3UI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPrototype4(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Prototype4UI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPrototype5(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Prototype5UI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
