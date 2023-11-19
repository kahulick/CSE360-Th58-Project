package effortLoggerV2;

//used for reading file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import effortLoggerV2.EffortConsoleController;
import EffortLogger.EffortLogsRepository;
@SuppressWarnings("unused")


/**
 * @author Kaelyn Hulick 
 * 
 * Title: EffortLogEditorController Class
 * 
 * Description: 
 * 
 */



public class EffortLogEditorController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private EffortLogsRepository effortLogsRepository = new EffortLogsRepository();
	
	public void launchEffortConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// test to retrieve data from text file 
	public void retireveTxtData() throws FileNotFoundException {
		effortLogsRepository.retrieveTxtData();
	}
	
}
