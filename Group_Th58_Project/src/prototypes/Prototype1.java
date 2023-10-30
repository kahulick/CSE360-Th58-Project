package prototypes;

import EffortLogger.Log;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import userInterfaceJavaFX.UserInterface;
@SuppressWarnings("unused")

// Prototype 1 : Generated Story Points Verification
// Kaelyn Hulick 

// 7.6.1 Generated Story Points Verification

// 7.6.1.1 One risk associated with this application is miscalculating the estimated number of story
// points based on relevant historical data from previous projects. If our application fails to generate
// an accurate calculation for estimated story points, the user may make their decision based on
// inaccurate data, causing either too little or too many story points for the success of the project.

// 7.6.1.2 This Risk-Reduction Prototype will fall under the responsibility of Kaelyn Hulick. This
// prototype will consist of verifying that the program's calculated estimate falls within an
// acceptable margin of accuracy before being displayed to the user. This prototype will mitigate
// the risk of generating inaccurate numbers of estimated story points by ensuring the program is
// producing accurate data based on margins reflecting historical data.

public class Prototype1 extends Application{
	
	public void testingAgain() {
		System.out.println("Prototype1: Generated Story Points Verification - Kaelyn Hulick");
		
		Log log1 = new Log("testProject", 1, "10/29/23", "10.25.03", "Planning", "buisiness", "need to make UI");
		Log log2 = new Log("testProject", 2, "10/24/23", "12.36.03", "Planning2", "buisiness", "need to make UI");
		
		String time1 = log1.getDeltaTime();
		String time2 = log2.getDeltaTime();
		
		System.out.println("Time for effortLog task for project1: " + time1);
		System.out.println("Time for effortLog task for project2: " + time2);
		
		
		int averageHours = (10 + 12) / 2;
		int averageMins = (25 + 36) / 2;
		int averageSeconds = (03 + 03) / 2;
		
		System.out.println(averageHours + "." + averageMins + "." + averageSeconds + ".");
		
		
		double storyPointsHigh = 12.0/8.0;	// each story point = 8 hrs 
		double storyPointsLow =  10.0/8.0;
		
		System.out.printf("%.2f\n", storyPointsHigh);
		System.out.printf("%.2f\n", storyPointsLow);
		
		double actualAverage = (double) storyPointsHigh / storyPointsLow;
		
		System.out.printf("%.2f\n", actualAverage);
		
		if (actualAverage <= storyPointsLow || actualAverage >= storyPointsHigh) {
			System.out.println("ERROR: STORYPOINTS OUT OF BOUNDS");
		}
		
	}

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PROTO 1!!!");
		
//		Log log1 = new Log("testProject", 1, "10/29/23", "10.25.03", "Planning", "buisiness", "need to make UI");
//		Log log2 = new Log("testProject", 2, "10/24/23", "12.36.03", "Planning2", "buisiness", "need to make UI");
//		
//		String time1 = log1.getDeltaTime();
//		String time2 = log2.getDeltaTime();
//		
//		System.out.println(time1);
//		System.out.println(time2);
    }

}
