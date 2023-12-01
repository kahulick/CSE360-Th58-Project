package EffortLogger;

import java.util.Random;

/**
 * @author Kaelyn Hulick 
 * 
 */


public class Project {
	private String projectType;
	private String projectID;
	private String projectName;
	private EffortLog[] effortLogs;
	private DefectLog[] defectLogs;
	
	public Project(String projectType, String projectName, EffortLog[] effortLogs, DefectLog[] defectLogs) {
		this.projectType = projectType;
		this.projectName = projectName;
		this.effortLogs = effortLogs;
		this.defectLogs = defectLogs;
		this.projectID = generateID();
	}
	
	public Project(String projectType, String projectID, String projectName, EffortLog[] effortLogs, DefectLog[] defectLogs) {
		this.projectType = projectType;
		this.effortLogs = effortLogs;
		this.defectLogs = defectLogs;
		this.projectID = projectID;
		this.projectName = projectName;
	}
	
	
	
	public static String generateID() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(9999);
	    return String.format("%04d", number);
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public EffortLog[] getEffortLogs() {
		return effortLogs;
	}

	public void setEffortLogs(EffortLog[] effortLogs) {
		this.effortLogs = effortLogs;
	}

	public DefectLog[] getDefectLogs() {
		return defectLogs;
	}

	public void setDefectLogs(DefectLog[] defectLogs) {
		this.defectLogs = defectLogs;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
