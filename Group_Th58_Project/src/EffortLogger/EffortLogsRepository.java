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

@SuppressWarnings("unused")

/**
 * @author Kevin Ruiz Hernandez
 * 
 * Title: EffortLogRepositoty Class
 * 
 * Description: This is a class that creates, reads, and writes to a text file storing all effort log data.
 * 
 * TODO -> refine description more  (Kevin)
 * 
 */



public class EffortLogsRepository {
	

	private static int effortLogAmt = 0;
	
	public void CreateEF(EffortLog effortLog) {
		
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
	    		effortLog.getID() + "," +
	    		effortLog.getProjectType() + "," +
	    		date + "," +
	    		startTime + "," + 
	    		stopTime + "," +
	    		effortLog.getDeltaTime() + "," +
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
	
	//will print out text file as string data
	public void retrieveTxtData() throws FileNotFoundException {	
		
		File file = new File("effort_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	System.out.println(sc.nextLine());
	    }
	    
	    sc.close();
	            
	}
	
	public int getEffortLogs() throws IOException {
		int effortLogCount = 0;
		File file = new File("effort_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	System.out.println(sc.nextLine());
	    	effortLogCount++;
	    }
	    sc.close();
	    return effortLogCount;
	}
	
	public List<String> getEffortLogStrings() throws IOException, NoSuchElementException {
		List<String> logStrings = new ArrayList<String>();
		File file = new File("effort_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	System.out.println(sc.nextLine());
	    	logStrings.add(sc.nextLine());
	    }
	    sc.close();
	    return logStrings;
	}
	
	public EffortLog[] getEffortRepo(int numLogs) throws FileNotFoundException {	// take count input from PPT
		
		EffortLog[] effortRepo = new EffortLog[numLogs];
		
		File file = new File("effort_logs.txt");
		Scanner sc = new Scanner(file);
		
		String ID;
		String projType;
		LocalDate dt;
		LocalTime startT;
		LocalTime stopT;
		String deltaTime;
		String LCS;
		String effortCat;
		String ECItem;
		
		String[] effortLogData = new String[8];
		
		//for formatting the string in the txt file to LocalDate object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formatter = formatter.withLocale(Locale.ENGLISH); 

		//for formatting the string in the txt file to LocalTime object
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm:ss");
		parser = parser.withLocale(Locale.ENGLISH);
		
		for(int i = 0; i < numLogs; i++) {
			 
			//making effortLogData equal to the txt file line by line, thereby making sure only one effort log
			//is parsed at a time
			effortLogData = sc.nextLine().split(",");
			
			ID = effortLogData[0];
			projType = effortLogData[1];
			dt = LocalDate.parse(effortLogData[2], formatter);
			startT = LocalTime.parse(effortLogData[3], parser);
			stopT = LocalTime.parse(effortLogData[4], parser);
			deltaTime = effortLogData[5];
			LCS = effortLogData[6];
			effortCat = effortLogData[7];
			ECItem = effortLogData[8];
			
			effortRepo[i] = new EffortLog(ID, projType, dt, startT, stopT, deltaTime, LCS, effortCat, ECItem);
			
		}
		
		sc.close();
		
		return effortRepo;
	}

}
