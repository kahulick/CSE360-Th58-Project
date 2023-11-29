package effortLoggerV2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import effortLoggerV2.EffortConsoleController;
import EffortLogger.Definitions;
import EffortLogger.EffortLog;
import EffortLogger.EffortLogsRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
@SuppressWarnings("unused")


/**
 * @author Kaelyn Hulick 
 * 
 */


//	TABLE VIEW -> NOT IT!! LISTVIEW IS CORRECT

// NOT RELEVANT RIGHT NOW

public class LogsController {	
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
	private ListView<String> userEffortLogs;
	
	public EffortLog effortLog;
	
	private EffortLogsRepository effortLogsRepository = new EffortLogsRepository();
	
	private ObservableList<EffortLog> logs = FXCollections.observableArrayList();
	
	private ObservableList<EffortLog> logHistory = FXCollections.observableArrayList();
	
	private ObservableList<String> logStrings = FXCollections.observableArrayList();

	// private ObservableList<TableColumn<EffortLog, ?>> columns = FXCollections.observableArrayList();
	
	public void launchEffortConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	} 
	
	@FXML 
	public void initialize() throws IOException {
		int count = effortLogsRepository.getEffortLogs();
		EffortLog[] effortLogsList = effortLogsRepository.getEffortRepo(count);
		for (EffortLog log : effortLogsList) {
			logHistory.add(log); 				// updates array of logs
			logStrings.add(mapToString(log));	// updates array of strings (that represent logs)
		}
		userEffortLogs.setItems(logStrings);
	}
	
	public String mapToString(EffortLog log) {	// refactored from Kevin's repository class
		//DateTimeFormatter 
	    DateTimeFormatter makeDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        String date = makeDate.format(log.getDate());
        
        //change startTime to string
        DateTimeFormatter makeTime = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
        String startTime = makeTime.format(log.getStartTime());
        String stopTime = makeTime.format(log.getStopTime());
        
        String strLog = log.getID() + "," +
         		log.getProjectType() + "," +
	    		date + "," +
	    		startTime + "," + 
	    		stopTime + "," +
	    		log.getDeltaTime() + "," +
	    		log.getLifeCycleStep() + "," +
	    		log.getEffortCategory() + "," +
	    		log.getEffortCategoryItem();
        return strLog;
	}
	

	
	public void setEffortLog(EffortLog effortLog) {
		this.effortLog = effortLog;
		System.out.println(effortLog == null);
		logs.add(effortLog);
//		addToTable();
//		effortLogsTable.refresh();
	}
	
}

