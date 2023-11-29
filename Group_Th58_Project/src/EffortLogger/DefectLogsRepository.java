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
	    		defectLog.getDetail()
	    );

	    //After done writing, remember to close!
	    out.close();
	    
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
	

}


























