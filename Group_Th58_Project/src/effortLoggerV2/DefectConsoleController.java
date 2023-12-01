package effortLoggerV2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import EffortLogger.DefectLog;
import EffortLogger.Definitions;
import EffortLogger.EffortLog;
import EffortLogger.EffortLogsRepository;
import EffortLogger.DefectLogsRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import effortLoggerV2.EffortConsoleController;
@SuppressWarnings("unused")


/**
 * @author Kaelyn Hulick 
 * 
 * Defect Controller Class - A class that takes user input, stores the data, and allows the user to create/ update defect logs. Utilizes
 * the DefectConsoleRepo
 * 
 */



public class DefectConsoleController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Definitions definitions = new Definitions();
	private DefectLog defectLog;	// current defect log or new one??
	private DefectLog currentDefectLog;
	private EffortLog effortLog;
	private LogsController logsController = new LogsController();
	private EffortLogsRepository effortLogsRepository = new EffortLogsRepository();
	private DefectLogsRepository defectLogsRepository = new DefectLogsRepository();
//	private int numBusiness;
//	private int numDevelopment;
	
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
	private TextArea detailInput = new TextArea();
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
	private boolean newLog = false;
	
	private List<String> defectLogStrings = FXCollections.observableArrayList();
	private List<DefectLog> defectLogs = FXCollections.observableArrayList();
	private List<String> businessLogStrings = FXCollections.observableArrayList();
	private List<DefectLog> businessDefectLogs = FXCollections.observableArrayList();
	private List<String> develLogStrings = FXCollections.observableArrayList();
	private List<DefectLog> develDefectLogs = FXCollections.observableArrayList();

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
		
		try {
			defectLogStrings = defectLogsRepository.getDefectLogStrings();
			defectLogs = defectLogsRepository.getDefectLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initializeDefectLogs();
		initializeProjectItems();
		defectCategory.setItems(definitions.options3d);
	}
	
	@FXML
	public void initializeDefectLogs() {	// 2.b
		for (String log : defectLogStrings) {
			String arr[] = log.split(",");
	    	if (arr[0].equalsIgnoreCase("Business Project")) {
	    		businessLogStrings.add(log);
	    		definitions.defectOptions1.add(String.format("%d. %s", definitions.defectOptions1.size(), arr[1]));
	    	} else if (arr[0].equalsIgnoreCase("Development Project")) {
	    		develLogStrings.add(log);
	    		definitions.defectOptions2.add(String.format("%d. %s", definitions.defectOptions2.size(), arr[1]));
	    	}
		}
		for (DefectLog defLog : defectLogs) {
			if (defLog.getProject().equalsIgnoreCase("Business Project")) {
				businessDefectLogs.add(defLog);
			} else if (defLog.getProject().equalsIgnoreCase("Development Project")) {
				develDefectLogs.add(defLog);
			}
		}

	}
	
	// public DefectLog
	
	@FXML
	public void initializeProjectItems() {
		if (projectItems.getValue() == null) {
			projectItems.setItems(definitions.options1a);
		}
	}
	
	@FXML // initializes the rest of the categories based on business versus development project
	public void initializeLifeCycleSteps() {
		defectItems.getItems().removeAll();
		if (projectItems.getValue() == "Business Project") {
			injectedSteps.setItems(definitions.options1a1);
			removedSteps.setItems(definitions.options1a1);
			defectItems.setItems(definitions.defectOptions1);
			defectItems.getSelectionModel().select(definitions.defectOptions1.get(0));
			fixItems.setItems(definitions.defectOptions1);
		} 
		if (projectItems.getValue() == "Development Project") {
			injectedSteps.setItems(definitions.options1a2);
			removedSteps.setItems(definitions.options1a2);
			defectItems.setItems(definitions.defectOptions2);
			defectItems.getSelectionModel().select(definitions.defectOptions2.get(0));
			fixItems.setItems(definitions.defectOptions2);
		}
	}
	
	@FXML
	public void existingLog(ActionEvent event) throws FileNotFoundException {
		
		if (defectItems.getSelectionModel().getSelectedIndex() != 0) {
			newLog = false;
			System.out.println("Existing");
			numLabel.setText(Integer.toString(defectItems.getSelectionModel().getSelectedIndex()));
			if (projectItems.getValue().equalsIgnoreCase("Business Project")) {
				defectLog = defectLogs.get(defectLogStrings.indexOf(businessLogStrings.get((defectItems.getSelectionModel().getSelectedIndex())-1)));
				System.out.println(defectLog.getDefectName() + " \n");
			} 
			if (projectItems.getValue().equalsIgnoreCase("Development Project")) {	///// 	WHYYYYYYYYyyyyyyy
				defectLog = defectLogs.get(defectLogStrings.indexOf(develLogStrings.get((defectItems.getSelectionModel().getSelectedIndex())-1)));
				System.out.println(defectLog.getDefectName() + " \n");
			}
			
			// defectNameInput
		} else {
			System.out.println("New");
			numLabel.setText("0");
		}
	}
	
	
	public void clearDefectLog(ActionEvent event) throws IOException {
		System.out.println("Clear");
		
	}
	
	public void createNewDefect(ActionEvent event) {
		System.out.println("Create");
		int test = defectItems.getItems().size();
		numLabel.setText(Integer.toString(test));
		newLog = true;
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
	
	// saves the new defect log or updates the current one
	// public DefectLog(String project, String defectName, boolean status, String detail, String injectedStep, String removedStep, String defectCategory, String fix)
	public void updateCurrentDefect(ActionEvent event) {
		if (newLog == true) {
			saveNewLog();
		} else {
			saveExistingLog();
		}
		try {
			defectLogStrings = defectLogsRepository.getDefectLogStrings();
			defectLogs = defectLogsRepository.getDefectLogs();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void saveExistingLog() {
		System.out.println("Save Existing");
		currentDefectLog = new DefectLog(projectItems.getValue(), defectNameInput.getText(), open, detailInput.getText(), 
				injectedSteps.getSelectionModel().getSelectedItem(), removedSteps.getSelectionModel().getSelectedItem(), 
				defectCategory.getSelectionModel().getSelectedItem(), fixItems.getValue());
		String currentStr = (currentDefectLog.getProject() + "," + currentDefectLog.getDefectName() + "," + Boolean.toString(currentDefectLog.getStatus()) + "," +
				currentDefectLog.getDetail() + "," + currentDefectLog.getInjectedStep() + "," + currentDefectLog.getRemovedStep() + "," +
				currentDefectLog.getDefectCategory() + "," + currentDefectLog.getFix());
		
		if (projectItems.getValue().equalsIgnoreCase("Business Project")) {
			String strLog = businessLogStrings.get((defectItems.getSelectionModel().getSelectedIndex())-1);
			int updateIndex = defectLogStrings.indexOf(strLog);	// 
			defectLog = defectLogs.get(updateIndex);
			System.out.println(defectLog.getDefectName() + " \n");
			try {
				defectLogsRepository.updateLog(currentStr, updateIndex);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			businessDefectLogs.add(defectLog);

		} else if (projectItems.getValue().equalsIgnoreCase("Development Project")) {
			String strLog = develLogStrings.get((defectItems.getSelectionModel().getSelectedIndex())-1);
			int updateIndex = defectLogStrings.indexOf(strLog);
			defectLog = defectLogs.get(updateIndex);
			System.out.println(defectLog.getDefectName() + " \n");
			try {
				defectLogsRepository.updateLog(currentStr, updateIndex);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			develDefectLogs.add(defectLog);
		}
		

	}
	
	public void saveNewLog() {
		defectLog = new DefectLog(projectItems.getValue(), defectNameInput.getText(), open, detailInput.getText(), 
				injectedSteps.getSelectionModel().getSelectedItem(), removedSteps.getSelectionModel().getSelectedItem(), 
				defectCategory.getSelectionModel().getSelectedItem(), fixItems.getValue());
		defectLogsRepository.CreateDF(defectLog);
		if (defectLog.getProject().equalsIgnoreCase("Business Project")) {
			businessDefectLogs.add(defectLog);
		} else if (defectLog.getProject().equalsIgnoreCase("Development Project")) {
			develDefectLogs.add(defectLog);
		}
		System.out.println(Integer.toString(businessDefectLogs.size()));
		System.out.println(Integer.toString(develDefectLogs.size()));
	}
	
	
	
	public void deleteCurrentDefect(ActionEvent event) {
		System.out.println("Delete");
	}
	
	
	
	
	
	
	
	
	
	
}