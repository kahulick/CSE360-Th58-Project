package EffortLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import effortLoggerV2.EffortConsoleController;
import effortLoggerV2.LogsController;
import effortLoggerV2.EffortLogEditorController;
import EffortLogger.EffortLog;
import EffortLogger.DefectLog;
import effortLoggerV2.DefectConsoleController;

@SuppressWarnings("unused")

/**
 * @author Kaelyn Hulick
 * 
 * Title: DefectLogRepositoty Class
 * 
 * Description: This is a class that creates, reads, and writes to a text file storing all defect log data.
 * 
 * 
 */


public class DefectLogsRepository {
	
	public static int defectLogAmt = 0;
	
	public void CreateDF(DefectLog defectLog) {
		
		//other methods would not allow for appending data and only overwrite current data
		
		PrintWriter out = null;
	    BufferedWriter bufWriter;

	    try{
	        bufWriter =
	            Files.newBufferedWriter(
	                Paths.get("defect_logs.txt"),
	                Charset.forName("UTF8"),
	                StandardOpenOption.WRITE, 
	                StandardOpenOption.APPEND,
	                StandardOpenOption.CREATE);
	        out = new PrintWriter(bufWriter, true);
	    }catch(IOException e){
	        //Oh, no! Failed to create PrintWriter'
	    	System.out.println("Could not create PrintWriter");
	    }
	    
	    
        //writes the defect log object as a string to the effort_logs.txt file, each log is separated by a newline
	    out.println(
	    		defectLog.getProject() + "," +
	    		defectLog.getDefectName() + "," +
	    		defectLog.getStatus() + "," +
	    		defectLog.getDetail() + "," +
	    		defectLog.getInjectedStep() + "," +
	    		defectLog.getRemovedStep() + "," +
	    		defectLog.getDefectCategory() + "," +
	    		defectLog.getFix()
	    );

	    //After done writing, remember to close!
	    out.close();
		// public DefectLog(String project, String defectName, boolean status, String detail, String injectedStep, String removedStep, String defectCategory, String fix)

	    //indicate that a new log has been added
	    defectLogAmt++;
	    System.out.println("Defect Log created...");
	    
	    //not functional, used for testing logs created in current session ONLY
	    System.out.println("Current logs: " + defectLogAmt);
	}
	
	//will print out text file as string data
	public void retrieveTxtData() throws FileNotFoundException {	
		
		File file = new File("defect_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	System.out.println(sc.nextLine());
	    }
	    sc.close();      
	}
	
	// returns total number of defect logs, business project defect logs, and development project defect logs
	public int[] getDefectLogCount() throws IOException {
		
		// int numDefectLogs[] = new int[3];
		int defectLogCount = 0; 	  // numDefectLogs[0]
		int businessLogCount = 0; 	  // numDefectLogs[1]
		int developmentLogCount = 0;  // numDefectLogs[2]
		
		File file = new File("defect_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	String test = sc.nextLine();
	    	String arr[] = test.split(" ");
	    	if (arr[0].equalsIgnoreCase("Business")) {
	    		businessLogCount++;
	    	} else if (arr[0].equalsIgnoreCase("Development")) {
	    		developmentLogCount++;
	    	}
	    	//System.out.println(sc.nextLine());
	    	System.out.println(arr[0]);
	    	defectLogCount++;
	    }
	    sc.close();
	    // numDefectLogs[0] = defectLogCount;
	    int numDefectLogs[] = {defectLogCount, businessLogCount, developmentLogCount};
	    return numDefectLogs;
	    
	    
	    // return defectLogCount;
	}
	

}


























