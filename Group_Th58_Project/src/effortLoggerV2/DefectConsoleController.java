package effortLoggerV2;

import java.io.IOException;
import EffortLogger.Definitions;
import EffortLogger.EffortLog;
import EffortLogger.EffortLogsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import effortLoggerV2.EffortConsoleController;
@SuppressWarnings("unused")


/**
 * @author Kaelyn Hulick 
 * 
 */



public class DefectConsoleController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Definitions definitions = new Definitions();
	private EffortLog effortLog;
	private LogsController logsController = new LogsController();
	private EffortLogsRepository effortLogsRepository = new EffortLogsRepository();
	
	public void launchEffortConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}