package effortLoggerV2;

import java.io.FileNotFoundException;
import java.io.IOException;

import EffortLogger.DefectLog;
import EffortLogger.Definitions;
import EffortLogger.EffortLog;
import EffortLogger.EffortLogsRepository;
import EffortLogger.DefectLogsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	private DefectLog defectLog;
	private EffortLog effortLog;
	private LogsController logsController = new LogsController();
	private EffortLogsRepository effortLogsRepository = new EffortLogsRepository();
	private DefectLogsRepository defectLogsRepository = new DefectLogsRepository();
	
	@FXML
	private ComboBox<String> projectItems = new ComboBox<String>();	// definitions options1a -> project type 
	@FXML															//				business		development
	private ComboBox<String> defectItems = new ComboBox<String>();	// definitions defectOptions1 & defectOptions2 -> loaded from defect log repo
	@FXML
	private ComboBox<String> fixItems = new ComboBox<String>();	// definitions defectOptions1 & defectOptions2 -> loaded from defect log repo
	@FXML
	private Label numLabel = new Label();
	@FXML
	private Label statusLabel = new Label();
	@FXML
	private TextField defectNameInput = new TextField();
	@FXML
	private TextField descriptionInput = new TextField();
	@FXML												// business		 development
	private ListView<String> injectedSteps;	// definitions options1a1 or options1a2 -> injected & removed drop down
	@FXML
	private ListView<String> removedSteps;	// definitions options1a1 or options1a2
	@FXML
	private ListView<String> defectCategory; // definitions options3d
	
	public void launchEffortConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// definitions options1a -> project type
	// definitions options1a1 -> business project steps 
	// definitions defectOptions1 -> business project defect logs
	// definitions options1a2 -> development project steps 
	// definitions defectOptions1 -> development project defect logs
	// definitions options3d -> defect category
	
	public void clearDefectLog(ActionEvent event) {
		System.out.println("Clear");
		
	}
	
	public void createNewDefect(ActionEvent event) {
		System.out.println("Create");
	}
	
	public void closeStatus(ActionEvent event) {
		System.out.println("Close");
	}
	
	public void openStatus(ActionEvent event) {
		System.out.println("Open");
	}
	
	public void updateCurrentDefect(ActionEvent event) {
		System.out.println("Update/Save");
	}
	
	public void deleteCurrentDefect(ActionEvent event) {
		System.out.println("Delete");
	}
	
	public void testSave(ActionEvent event) {
		System.out.println("Saving");
		defectLog = new DefectLog("Business", "My Defect");
		defectLog.setDetail("Woohoo these are my details");
		defectLogsRepository.CreateDF(defectLog);
		try {
			defectLogsRepository.retrieveTxtData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}