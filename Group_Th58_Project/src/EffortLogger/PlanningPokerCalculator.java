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
	
	public double calculateStoryPoints(ObservableList<Double> logEstimates) {	// not weighted
		int numLogs = logEstimates.size();
		double sumEstimates = 0.0;
		for (Double est: logEstimates) {
			sumEstimates += est;
		}
		double estimatedStoryPoints = sumEstimates / numLogs;
		if (estimatedStoryPoints > 5) {
			estimatedStoryPoints = Math.floor(estimatedStoryPoints);	// rounds down to 5
		} else {
			estimatedStoryPoints = Math.floor(estimatedStoryPoints * 100) / 100; // rounds to 2 decimal places
		}
		return estimatedStoryPoints;
	}
	
	public ObservableList<Double> calculateIndividualEffort(ObservableList<EffortLog> refinedData, ObservableList<EffortLog> historicalData) {	// not weighted
		if (refinedData == null) {
			refinedData = historicalData;	// means user never refined the logs/ updated keywords, so there is no refined data
		}
		ObservableList<Double> effortScores = FXCollections.observableArrayList();
		for (EffortLog log: refinedData) {
			double timeVal = getTimeVal(log.getDeltaTime());
			double projectVal = getProjectVal(log.getProjectType());
			double effortCategoryVal = getEffortCategoryVal(log.getEffortCategory());
			double subCategoryVal = 0.0;
			switch(log.getEffortCategory().toLowerCase()) {
			case "plans":
				subCategoryVal = getPlansVal(log.getEffortCategoryItem());
				break;
			case "deliverables":
				subCategoryVal = getDeliverablesVal(log.getEffortCategoryItem());
				break;
			case "interruptions":
				subCategoryVal = getInterruptionVal(log.getEffortCategoryItem());
				break;
			case "defects":
				subCategoryVal = getDefectsVal(log.getEffortCategoryItem());
				break;
			case "others":
				subCategoryVal = 1.0;	// no "others" sub category in formula 
				break;
			default:
				subCategoryVal = 0.0;
			}
			double rating = (timeVal + projectVal + effortCategoryVal + subCategoryVal);
			effortScores.add(rating);
		}
		
		return effortScores;
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
	
	public double getDeliverablesVal(String effortCategoryItem) {
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
