package EffortLogger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
@SuppressWarnings("unused")

public class PlanningPokerCalculator {

	private ObservableList<EffortLog> historicalData = FXCollections.observableArrayList();
	private ObservableList<String> displayedData = FXCollections.observableArrayList();
	
//	Business Project,12/11/2023,23:54:44,23:55:02,Verifying,Others,SCREEN CAST TEST 5
//	Business Project,15/11/2023,18:09:32,18:09:42,Outlining,Others,Cool
	
	public int calculateStoryPoints(ObservableList<EffortLog> refinedData, ObservableList<EffortLog> historicalData) {	// not weighted
		this.historicalData = historicalData;
		
		
		
		
		return 1;
		
	}

}
