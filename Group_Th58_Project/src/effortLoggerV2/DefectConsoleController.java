package effortLoggerV2;

import java.io.FileNotFoundException;
import java.io.IOException;

import EffortLogger.DefectLog;
import EffortLogger.Definitions;
import EffortLogger.EffortLog;
import EffortLogger.EffortLogsRepository;
import EffortLogger.DefectLogsRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	private int businessDefectLogs;
	private int developmentDefectLogs;
	
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
	
	private String selectedInjectedStep;
	private String selectedRemovedStep;
	private String selectedDefectCategory;
	private boolean open = false;

	public void launchEffortConsole(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EffortConsoleUI.fxml")); 
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	@FXML
	public void initialize() {		
		injectedSteps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String selectedStep) {
				System.out.println("Selected Injected Step: " + selectedStep);
				selectedInjectedStep = selectedStep;
			}
		});
		
		removedSteps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String selectedStep) {
				System.out.println("Selected Removed Step: " + selectedStep);
				selectedRemovedStep = selectedStep;
			}
		});
		
		defectCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String selectedStep) {
				System.out.println("Selected Defect Category: " + selectedStep);
				selectedDefectCategory = selectedStep;
			}
		});
		
		initializeProjectItems();
		defectCategory.setItems(definitions.options3d);
		
	}
	
	
	// definitions options1a -> project type
	// definitions options1a1 -> business project steps 
	// definitions defectOptions1 -> business project defect logs
	// definitions options1a2 -> development project steps 
	// definitions defectOptions1 -> development project defect logs
	// definitions options3d -> defect category
	
	@FXML
	public void initializeProjectItems() {
		if (projectItems.getValue() == null) {
			projectItems.setItems(definitions.options1a);
		}
	}
	
	@FXML // initializes the rest of the categories based on business versus development project
	public void initializeLifeCycleSteps() {
		if (projectItems.getValue() == "Business Project") {
			injectedSteps.setItems(definitions.options1a1);
			removedSteps.setItems(definitions.options1a1);
			defectItems.setItems(definitions.defectOptions1);
			defectItems.getSelectionModel().select(definitions.defectOptions1.get(0));
		} 
		if (projectItems.getValue() == "Development Project") {
			injectedSteps.setItems(definitions.options1a2);
			removedSteps.setItems(definitions.options1a2);
			defectItems.setItems(definitions.defectOptions2);
			defectItems.getSelectionModel().select(definitions.defectOptions2.get(0));
		}
		try {
			numLabel.setText(Integer.toString(initializeCurrentDefect(projectItems.getValue())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// returns the correct number of defect logs by category
	public int initializeCurrentDefect(String projectType) throws IOException {
		int num = 0;
		int defectLogs[] = defectLogsRepository.getDefectLogCount();
		if (projectType.equalsIgnoreCase("Business Project")) {
			num = defectLogs[1];
		} else if (projectType.equalsIgnoreCase("Development Project")) {
			num = defectLogs[2];
		}
		return num;
	}

	
	public void clearDefectLog(ActionEvent event) throws IOException {
		System.out.println("Clear");
		
		// TESTING PURPOSES !!! 
		int defectLogs[] = defectLogsRepository.getDefectLogCount();
		System.out.printf("Total: %d\n", defectLogs[0]);
		System.out.printf("Business: %d\n", defectLogs[1]);
		System.out.printf("Development: %d\n", defectLogs[2]);
		// System.out.println(defectLogsRepository.getDefectLogCount());
	}
	
	public void createNewDefect(ActionEvent event) {
		System.out.println("Create");
	}
	
	public void closeStatus(ActionEvent event) {
		System.out.println("Close");
		statusLabel.setText("Closed");
		open = false;
	}
	
	public void openStatus(ActionEvent event) {
		System.out.println("Open");
		statusLabel.setText("Open");
		open = true;
	}
	
	
	// public DefectLog(String project, String defectName, boolean status, String detail, String injectedStep, String removedStep, String defectCategory, String fix)
	public void updateCurrentDefect(ActionEvent event) {
		System.out.println("Update/Save");
		System.out.println(defectNameInput.getText());
		System.out.println(descriptionInput.getText());
		System.out.println(injectedSteps.getSelectionModel().getSelectedItem());
		defectLog = new DefectLog(projectItems.getValue(), defectNameInput.getText(), open, descriptionInput.getText(), injectedSteps.getSelectionModel().getSelectedItem(), removedSteps.getSelectionModel().getSelectedItem(), defectCategory.getSelectionModel().getSelectedItem(), fixItems.getValue());
		defectLogsRepository.CreateDF(defectLog);
	}
	
	public void deleteCurrentDefect(ActionEvent event) {
		System.out.println("Delete");
	}
	
	public void testSave(ActionEvent event) throws IOException {
		System.out.println("Saving");
		defectLogsRepository.retrieveTxtData();
	}
	
	
	
	
	
	
	
	
	
}