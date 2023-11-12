package EffortLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

import effortLoggerV2.EffortConsoleController;
import effortLoggerV2.LogsController;
import effortLoggerV2.EffortLogEditorController;
import EffortLogger.EffortLog;

@SuppressWarnings("unused")

public class EffortLogsRepository {
	
	public void CreateEF(EffortLog effortLog) {
		
		//code pulled from stackoverflow to create a PrintWriter
		//other methods would not allow for appending data and only overwrite current data
		
		PrintWriter out = null;
	    BufferedWriter bufWriter;

	    try{
	        bufWriter =
	            Files.newBufferedWriter(
	                Paths.get("effort_logs.txt"),
	                Charset.forName("UTF8"),
	                StandardOpenOption.WRITE, 
	                StandardOpenOption.APPEND,
	                StandardOpenOption.CREATE);
	        out = new PrintWriter(bufWriter, true);
	    }catch(IOException e){
	        //Oh, no! Failed to create PrintWriter'
	    	System.out.println("Could not create PrintWriter");
	    }

	    //After successful creation of PrintWriter
	    // out.println("Data data data");
	    
	    //changing effort log localDate into a string named 'date' by day
	    final DateTimeFormatter makeDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        String date = makeDate.format(effortLog.getDate());
        
        //change startTime to string
        final DateTimeFormatter makeTime = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
        String startTime = makeTime.format(effortLog.getStartTime());
        String stopTime = makeTime.format(effortLog.getStopTime());
	    
        //writes the effort log object as a string to the effort_logs.txt file, each log is seperated by a newline
	    out.println(
	    		
	    		effortLog.getProjectType() + " " +
	    		date + " " +
	    		startTime + " " + 
	    		stopTime + " " +
	    		effortLog.getLifeCycleStep() + " " +
	    		effortLog.getEffortCategory() + " " +
	    		effortLog.getEffortCategoryItem()
	    );

	    //After done writing, remember to close!
	    out.close();
	    System.out.println("Log created...");
	}
	
	public void retrieveTxtData() throws FileNotFoundException {	//  PLACEHOLDER -> MANIPULATE AS NEEDED
		
		//System.out.println("YAY DATA");  // placeholder so I can like it to the button lol 
		// CreateEF();
		File file = new File("effort_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	System.out.println(sc.nextLine());
	    }
	    
	    sc.close();
	            
		
	}

}
