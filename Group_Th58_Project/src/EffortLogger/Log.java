package EffortLogger;


@SuppressWarnings("unused")

public class Log {
	
	private String project;
	private int number;
	private String date;
	private String deltaTime;
	private String lifeCycle;
	private String category;
	private String deliverable;
	
	public Log(String project, int number, String date, String deltaTime, String lifeCycle, String category, String deliverable) {
		this.project = project;
		this.number = number;
		this.date = date;
		this.deltaTime = deltaTime;
		this.lifeCycle = lifeCycle;
		this.category = category;
		this.deliverable = deliverable;
	}
	
	
	
	public String newLog(int idk) {
		return "made it for " + idk;
	}

}
