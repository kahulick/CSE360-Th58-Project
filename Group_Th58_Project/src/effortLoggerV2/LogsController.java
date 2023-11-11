package effortLoggerV2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import effortLoggerV2.EffortConsoleController;
import EffortLogger.Definitions;
import EffortLogger.EffortLog;
@SuppressWarnings("unused")

public class LogsController {	// eventually make an observable list for effort logs to add into it
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Tab defectLogs = new Tab();
	@FXML
	private Tab effortLogs = new Tab();
	@FXML
	private Label projectLabel = new Label();
	@FXML
	private Label numberEffortLogs = new Label();
	@FXML
	private Label numberDefectLogs = new Label();
	@FXML
	private TableView<EffortLog> effortLogsTable = new TableView<EffortLog>();
	@FXML
	private TableColumn<EffortLog, Integer> numberCol = new TableColumn<EffortLog, Integer>();
	@FXML
	private TableColumn<EffortLog, LocalDate> dateCol = new TableColumn<EffortLog, LocalDate>();
	@FXML
	private TableColumn<EffortLog, LocalTime> startCol = new TableColumn<EffortLog, LocalTime>();
	@FXML
	private TableColumn<EffortLog, LocalTime> stopCol = new TableColumn<EffortLog, LocalTime>();
	@FXML
	private TableColumn<EffortLog, String> deltaCol = new TableColumn<EffortLog, String>();
	@FXML
	private TableColumn<EffortLog, String> lifeCycleCol = new TableColumn<EffortLog, String>();
	@FXML
	private TableColumn<EffortLog, String> effortCategoryCol = new TableColumn<EffortLog, String>();
	@FXML
	private TableColumn<EffortLog, String> effortItemCol = new TableColumn<EffortLog, String>();
	
	private EffortLog testLog;
	
	public void launchEffortConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void enterLog(EffortLog effortLog) {
		this.testLog = effortLog;
		// System.out.println(testLog.getDeltaTime());
		// effortLogsTable.getColumns().addAll(numberCol);
	}
	
	
	
}

// for testing purposes

//System.out.println("Date: " + effortLog.getDate());
//System.out.println("Start: " + effortLog.getStartTime());
//System.out.println("Stop: " + effortLog.getStopTime());
//System.out.println("Delta: " + effortLog.getDeltaTime());
//System.out.println("Project Type: " + effortLog.getProjectType());
//System.out.println("Life Cycle Step: " + effortLog.getLifeCycleStep());
//System.out.println("Effort Category: " + effortLog.getEffortCategory());
//System.out.println("Effort Category: " + effortLog.getEffortCategoryItem());