package EffortLogger;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
@SuppressWarnings("unused")

public class PlanningPokerCalculator {

	private ObservableList<EffortLog> historicalData = FXCollections.observableArrayList();
	private ObservableList<String> displayedData = FXCollections.observableArrayList();
	
//	private String projectType;
//	private LocalDate date;
//	private LocalTime startTime;
//	private LocalTime stopTime;
//	private String deltaTime;
//	private String lifeCycleStep;
//	private String effortCategory;
//	private String effortCategoryItem;
//	private int number = 1;
	
	public int calculateStoryPoints(ObservableList<EffortLog> refinedData, ObservableList<EffortLog> historicalData) {	// not weighted
		this.historicalData = historicalData;
		
		// for every effortlog item:
			// get the time val 
			// add time val to running sum
		// timeSum/refinedData.size() = avgTimeVal
		
		//^^ repeat this for every category & add the averages according to Dom's calculations to get estimate
		
		
		return 1;
		
	}
	
	public double getTimeVal(String deltaTime) {
		double timeVal = 0.0;
		if (Integer.parseInt(String.valueOf(deltaTime.charAt(0)))<= 1) {
			timeVal = 0.5;
		} else {
			timeVal = 1.0;
		}
		return timeVal;
	}
	
	public double getProjectVal(String projectType) {
		double projectVal = 0.0;
		projectType = projectType.toLowerCase();
		
		switch(projectType) {
		case "business project":
			projectVal = 0.8;
			break;
		case "development project":
			projectVal = 1.0;
			break;
		default:
			projectVal = 0.0;
		}
		return projectVal;
	}
	
	public double getEffortCategoryVal(String effortCategory) {
		double effortCategoryVal = 0.0;
		effortCategory = effortCategory.toLowerCase();
		
		switch(effortCategory) {
		case "plans":
			effortCategoryVal = 1.75;
			break;
		case "deliverables":
			effortCategoryVal = 2.0;
			break;
		case "interruptions":
			effortCategoryVal = 0.0;
			break;
		case "defects":
			effortCategoryVal = 1.5;
			break;
		case "others":
			effortCategoryVal = 0.0;
			break;
		default:
			effortCategoryVal = 0.0;
		}
		return effortCategoryVal;
	}
	
	public double getPlansVal(String effortCategoryItem) {
		double subCategoryVal = 0.0;
		effortCategoryItem = effortCategoryItem.toLowerCase();
		
		switch(effortCategoryItem) {
		case "project management plan":
			subCategoryVal = 1.5;
			break;
		case "risk management plan":
			subCategoryVal = 0.7;
			break;
		case "concept design plan":
			subCategoryVal = 0.9;
			break;
		case "detailed design plan":
			subCategoryVal = 1.3;
			break;
		case "implementation plan":
			subCategoryVal = 1.0;
			break;
		default:
			subCategoryVal = 0.0;
		}
		return subCategoryVal;
	}
	
	public double getDeliverablesValue(String effortCategoryItem) {
		double subCategoryVal = 0.0;
		effortCategoryItem = effortCategoryItem.toLowerCase();
		
		switch(effortCategoryItem) {
		case "conceptual design":
			subCategoryVal = 0.5;
			break;
		case "detailed design":
			subCategoryVal = 1.5;
			break;
		case "test cases":
			subCategoryVal = 0.4;
			break;
		case "solution":
			subCategoryVal = 0.8;
			break;
		case "reflection":
			subCategoryVal = 0.2;
			break;
		case "outline":
			subCategoryVal = 0.3;
			break;
		case "draft":
			subCategoryVal = 0.7;
			break;
		case "report":
			subCategoryVal = 1.2;
			break;
		case "user defined":
			subCategoryVal = 0.5;
			break;
		case "other":
			subCategoryVal = 1.0;
			break;
		default:
			subCategoryVal = 0.0;
		}
		return subCategoryVal;
	}
	
	public double getInterruptionVal(String effortCategoryItem) {
		return 0.0;
	}
	
	public double getDefectsVal(String effortCategoryItem) {
		double subCategoryVal = 0.0;
		effortCategoryItem = effortCategoryItem.toLowerCase();
		
		switch (effortCategoryItem) {
		case "documentation":
			subCategoryVal = 0.3;
			break;
		case "syntax":
			subCategoryVal = 0.1;
			break;
		case "build/package":
			subCategoryVal = 0.2;
			break;
		case "assignment":
			subCategoryVal = 0.4;
			break;
		case "interface":
			subCategoryVal = 0.7;
			break;
		case "checking":
			subCategoryVal = 0.4;
			break;
		case "data":
			subCategoryVal = 1.5;
			break;
		case "function":
			subCategoryVal = 1.3;
			break;
		case "system":
			subCategoryVal = 1.0;
			break;
		case "environment":
			subCategoryVal = 0.5;
			break;
		default:
			subCategoryVal = 0.0;
		}
		
		return subCategoryVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
