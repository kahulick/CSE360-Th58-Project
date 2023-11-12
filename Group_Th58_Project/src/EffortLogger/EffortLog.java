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
	private int number = 1;
	
	// Effort Log object used to map into the table (no projectType & has a set deltaTime attribute)
	public EffortLog(int number, LocalDate date, LocalTime startTime, LocalTime stopTime, String deltaTime, String lifeCycleStep, String effortCategory, String effortCategoryItem) {
		this.number = number;
		this.date = date;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.deltaTime = deltaTime;
		this.lifeCycleStep = lifeCycleStep;
		this.effortCategory = effortCategory;
		this.effortCategoryItem = effortCategoryItem;
	}
	
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
	
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getLifeCycleStep() {
		return lifeCycleStep;
	}

	public void setLifeCycleStep(String lifeCycleStep) {
		this.lifeCycleStep = lifeCycleStep;
	}

	public String getEffortCategory() {
		return effortCategory;
	}

	public void setEffortCategory(String effortCategory) {
		this.effortCategory = effortCategory;
	}

	public String getEffortCategoryItem() {
		return effortCategoryItem;
	}

	public void setEffortCategoryItem(String effortCategoryItem) {
		this.effortCategoryItem = effortCategoryItem;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getStopTime() {
		return stopTime;
	}

	public String getDeltaTime() {
		return deltaTime;
	}
	
	public int getNumber() {
		return 1;
	}
	
	
	

}
