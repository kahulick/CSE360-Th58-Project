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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import effortLoggerV2.EffortConsoleController;
import effortLoggerV2.LogsController;
import effortLoggerV2.EffortLogEditorController;
import EffortLogger.EffortLog;

@SuppressWarnings("unused")

public class EffortLogsRepository {
	
	//need to save to txt file somehow so that count stay accurate;
	//anything having to do with effortLogAmt is only works if txt file starts empty and only for current session
	private int effortLogAmt = 0;
	
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
	    
	    //changing effort log localDate into a string named 'date' by day
	    DateTimeFormatter makeDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        String date = makeDate.format(effortLog.getDate());
        
        //change startTime to string
        DateTimeFormatter makeTime = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
        String startTime = makeTime.format(effortLog.getStartTime());
        String stopTime = makeTime.format(effortLog.getStopTime());
	    
        //writes the effort log object as a string to the effort_logs.txt file, each log is separated by a newline
	    out.println(
	    		
	    		effortLog.getProjectType() + "," +
	    		date + "," +
	    		startTime + "," + 
	    		stopTime + "," +
	    		effortLog.getLifeCycleStep() + "," +
	    		effortLog.getEffortCategory() + "," +
	    		effortLog.getEffortCategoryItem()
	    );

	    //After done writing, remember to close!
	    out.close();
	    
	    //indicate that a new log has been added
	    effortLogAmt++;
	    System.out.println("Log created...");
	    
	    //not functional, used for testing logs created in current session ONLY
	    System.out.println("Current logs: " + effortLogAmt);
	    
	}
	
	//place holder test class
	public void retrieveTxtData() throws FileNotFoundException {	
		
		File file = new File("effort_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	System.out.println(sc.nextLine());
	    }
	    
	    System.out.print(getEffortRepo());
	    
	    sc.close();
	            
	}
	
	public EffortLog[] getEffortRepo() throws FileNotFoundException {
		
		EffortLog[] effortRepo = new EffortLog[effortLogAmt];
		
		File file = new File("effort_logs.txt");
		Scanner sc = new Scanner(file);
		
		//parameters for EffortLog objects
		//String projectType, LocalDate date, LocalTime startTime, LocalTime stopTime, String lifeCycleStep, String effortCategory, String effortCategoryItem
		
		String projType;
		LocalDate dt;
		LocalTime startT;
		LocalTime stopT;
		String LCS;
		String effortCat;
		String ECItem;
		
		String[] effortLogData = new String[6];
		
		//for formatting the string in the txt file to LocalDate object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formatter = formatter.withLocale(Locale.ENGLISH); 

		//for formatting the string in the txt file to LocalTime object
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm:ss");
		parser = parser.withLocale(Locale.ENGLISH);
		
		for(int i = 0; i < effortLogAmt; i++) {
			 
			effortLogData = sc.nextLine().split(",");
			
			projType = effortLogData[0];
			dt = LocalDate.parse(effortLogData[1], formatter);
			startT = LocalTime.parse(effortLogData[2], parser);
			stopT = LocalTime.parse(effortLogData[3], parser);
			LCS = effortLogData[4];
			effortCat = effortLogData[5];
			ECItem = effortLogData[6];
			
			effortRepo[i] = new EffortLog(projType, dt, startT, stopT, LCS, effortCat, ECItem);
			
		}
		
		sc.close();
		
		return effortRepo;
	}

}
