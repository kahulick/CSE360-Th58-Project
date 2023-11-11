package EffortLogger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
@SuppressWarnings("unused")

public class EffortLog {	// gonna have to map multiple Effort Logs to a project
	
	private String date;
	private String startTime;
	private String stopTime;
	private String deltaTime;
	private String lifeCycleStep;
	private String effortCategory;
	private String effortCategoryItem;
	
	EffortLog(String date, String startTime, String stopTime, String lifeCycleStep, String effortCategory, String effortCategoryItem) {
//		this.date = String.format("%s-%s-%s", date.substring(0,4), date.substring(4,6), date.substring(6,8));
//		this.startTime = String.format("%s:%s:%s", startTime.substring(9,11), startTime.substring(11,13), startTime.substring(13,15));
//		this.stopTime = String.format("%s:%s:%s", stopTime.substring(9,11), stopTime.substring(11,13), stopTime.substring(13,15));
//		gonna change
	}
	
	public String calculateDeltaTime(String start, String stop) {
		
		
		return " ";
	}
	
	
	
	
	
	//public EffortLog(String date)

}
