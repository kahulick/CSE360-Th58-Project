package EffortLogger;

/**
 * @author Kaelyn Hulick 
 * 
 */

public class DefectLog {
	String project;
	String defectName;
	int defectNum;
	boolean status = true;	// true = open, false = closed -> option to reopen
	String detail;
	String injectedStep;
	String removedStep;
	String defectCategory;
	String fix;
	
	public DefectLog(String project, String defectName) {
		this.project = project;
		this.defectName = defectName;
	}
	
	public DefectLog(String project, String defectName, int defectNum) {
		this.project = project;
		this.defectName = defectName;
		this.defectNum = defectNum;
	}
	
	public DefectLog(String project, String defectName, boolean status, String detail, String injectedStep, String removedStep, String defectCategory, String fix) {
		this.project = project;
		this.defectName = defectName;
		this.status = status;
		this.detail = detail;
		this.injectedStep = injectedStep;
		this.removedStep = removedStep;
		this.defectCategory = defectCategory;
		this.fix = fix;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getDefectName() {
		return defectName;
	}

	public void setDefectName(String defectName) {
		this.defectName = defectName;
	}

	public int getDefectNum() {
		return defectNum;
	}

	public void setDefectNum(int defectNum) {
		this.defectNum = defectNum;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getInjectedStep() {
		return injectedStep;
	}

	public void setInjectedStep(String injectedStep) {
		this.injectedStep = injectedStep;
	}

	public String getRemovedStep() {
		return removedStep;
	}

	public void setRemovedStep(String removedStep) {
		this.removedStep = removedStep;
	}

	public String getDefectCategory() {
		return defectCategory;
	}

	public void setDefectCategory(String defectCategory) {
		this.defectCategory = defectCategory;
	}
	
	public String getFix() {
		return fix;
	}
	
	public void setFix(String fix) {
		this.fix = fix;
	}
	
	
	
	

}
