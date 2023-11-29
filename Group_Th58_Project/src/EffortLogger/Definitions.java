package EffortLogger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author Kaelyn Hulick 
 * 
 * Title: Definitions Class
 * 
 * Description: A class to store objects which appear in the drop down menus of the Effort Logger V2 UI's. This
 * class serves as a library for the separate controllers to pull data from. 
 * 
 */



public class Definitions {
	
	
	// Project & Life Cycle options
	public ObservableList<String> options1a = FXCollections.observableArrayList("Business Project", "Development Project");
	
	public ObservableList<String> options1a1 = FXCollections.observableArrayList(
			"Planning", "Information Gathering", "Information Understanding", "Verifying",
			"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting");
	
	public ObservableList<String> options1a2 = FXCollections.observableArrayList(
			"Problem Understanding", "Conceptual Design Plan", "Requirements", "Conceptual Design", 
			"Conceptual Design Review", "Detailed Design Plan", "Detailed Design/Prototype", "Detailed Design Review", 
			"Implementation Plan", "Test Case Generation", "Solution Specification", "Solution Review", 
			"Solution Implementation", "Unit/System Test", "Reflection", "Repository Update");
	
	// Effort Categories and Options
	public ObservableList<String> options3 = FXCollections.observableArrayList("Plans", "Deliverables",
			"Interruptions", "Defects", "Others");
	
	public ObservableList<String> options3a = FXCollections.observableArrayList("Project Plan", "Risk Management Plan",				
			"Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan");
	
	public ObservableList<String> options3b = FXCollections.observableArrayList("Conceptual Design", "Detailed Design", 
			"Test Cases", "Solution", "Reflection", "Outline", "Draft", "Report", "User Defined", "Other");			
		
	public ObservableList<String> options3c = FXCollections.observableArrayList("Break", "Phone", "Teammate", "Visitor", "Other");
	
	public ObservableList<String> options3d = FXCollections.observableArrayList("Not specified", "10 Documentation", 
			"20 Syntax","30 Build, Package", "40 Assignment", "50 Interface", "60 Checking", "70 Data", "80 Function", 
			"90 System", "100 Environment");
	public ObservableList<String> options3e = FXCollections.observableArrayList("");
	
	// Defect Names
	// business project defect options
	public ObservableList<String> defectOptions1 = FXCollections.observableArrayList();	// load created business project defects into this option
	// development project defect options
	public ObservableList<String> defectOptions2 = FXCollections.observableArrayList();	// load created development project defects into this option
	

	
}
