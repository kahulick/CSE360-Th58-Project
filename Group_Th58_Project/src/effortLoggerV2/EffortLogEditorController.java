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
@SuppressWarnings("unused")

public class EffortLogEditorController extends EffortConsoleController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void launchEffortConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void retireveTxtData() throws FileNotFoundException {	//  PLACEHOLDER -> MANIPULATE AS NEEDED
		
		//System.out.println("YAY DATA");  // placeholder so I can like it to the button lol 
		CreateEF();
		
		File file = new File(
	            "effort_logs.txt");
	        Scanner sc = new Scanner(file);
	 
	        while (sc.hasNextLine())
	            System.out.println(sc.nextLine());
		
		
	}
}
