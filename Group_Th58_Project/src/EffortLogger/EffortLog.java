package EffortLogger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
@SuppressWarnings("unused")

public class EffortLog {	// gonna have to map multiple Effort Logs to a project & utilize the update log 
	
	private String projectType;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime stopTime;
	private String deltaTime;
	private String lifeCycleStep;
	private String effortCategory;
	private String effortCategoryItem;
	
	public EffortLog(String projectType, LocalDate date, LocalTime startTime, LocalTime stopTime, String lifeCycleStep, String effortCategory, String effortCategoryItem) {
		this.projectType = projectType;
		this.date = date;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.deltaTime = calculateDeltaTime(startTime, stopTime);
		this.lifeCycleStep = lifeCycleStep;
		this.effortCategory = effortCategory;
		this.effortCategoryItem = effortCategoryItem;
	}
	
	public String calculateDeltaTime(LocalTime start, LocalTime stop) {
		Duration duration = Duration.between(stopTime, startTime).abs();
		long durationSec = duration.getSeconds();
		String deltaTime = String.format("%d:%02d:%02d", durationSec/3600, (durationSec%3600)/60, durationSec%60);
		return deltaTime;
	}
	
	

}
