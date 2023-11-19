package EffortLogger;

import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("unused")

/**
 * @author Kaelyn Hulick 
 * 
 */



public class Log {
	
	private String project;
	private int number;
	private String date;
	private String deltaTime;
	private String lifeCycle;
	private String category;
	private String deliverable;
	//private SimpleStringProperty yuh;
	
	public Log(String project, int number, String date, String deltaTime, String lifeCycle, String category, String deliverable) {
		this.project = project;
		this.number = number;
		this.date = date;
		this.deltaTime = deltaTime;
		this.lifeCycle = lifeCycle;
		this.category = category;
		this.deliverable = deliverable;
	}
	
	public void setProject(String projectName) {
		this.project = projectName;
	}
	
	public String getProject() {
		return project;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDeltaTime(String deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	public String getDeltaTime() {
		return deltaTime;
	}
	
	public void setlifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle;
	}
	
	public String getLifeCycle(String lifeCycle) {
		return lifeCycle;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}
	
	public String getDeliverable(String deliverable) {
		return deliverable;
	}
	

	
	public String newLog(int idk) {
		return "made it for " + idk;
	}
	
	
	
	

}
