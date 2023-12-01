package EffortLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
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
	    
	    // out.
	    
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
	    	defectLogCount++;
	    }
	    sc.close();
	    int numDefectLogs[] = {defectLogCount, businessLogCount, developmentLogCount};
	    return numDefectLogs;
	}
	
	public List<String> getDefectLogStrings() throws IOException, NoSuchElementException {
		List<String> logStrings = new ArrayList<String>();
		File file = new File("defect_logs.txt");
	    Scanner sc = new Scanner(file);
	    while (sc.hasNextLine()) {
	    	logStrings.add(sc.nextLine());
	    }
	    sc.close();
	    return logStrings;
	}
	
	// public DefectLog(String project, String defectName, boolean status, String detail, String injectedStep, String removedStep, String defectCategory, String fix)
	
	public List<DefectLog> getDefectLogs() throws FileNotFoundException {
		List<DefectLog> defectRepo = new ArrayList<DefectLog>();
		File file = new File("defect_logs.txt");
	    Scanner sc = new Scanner(file);

	    boolean status;
	    
	    while (sc.hasNextLine()) {
	    	String data[] = sc.nextLine().split(",");
	    	if (data[2].equalsIgnoreCase("true")) {
	    		status = true;
	    	} else {
	    		status = false;
	    	}
	    	DefectLog defectLog = new DefectLog(data[0], data[1], status, data[3], data[4], data[5], data[6], data[7]);
	    	defectRepo.add(defectLog);
	    }
	    
	    sc.close();
		
	    System.out.println(defectRepo.size());
		return defectRepo;
	}
	
	public void updateLog(String oldLog, String newLog, int index) throws IOException {
		
//		BufferedReader in = new BufferedReader(new FileReader("defect_logs.txt"));
//		BufferedWriter out = new BufferedWriter(new FileWriter("defect_logs.txt"));
//		
//		
//		
//		String line;
//		
//		while((line=in.readLine()) !=null) {
//			System.out.println(line);
////			if (line.equalsIgnoreCase(oldLog)) {
////				line = line.replaceFirst(oldLog, newLog);
////				out.write(line + " \n");
////				// out.newLine();
////				break;
////			}
//		}
//		in.close();
//		out.close();
    
//		System.out.println(index);
//		int line = 0;
//		File file = new File("defect_logs.txt");
//	    Scanner sc = new Scanner(file);
//		StringBuffer input = new StringBuffer();
//		
//		while(sc.hasNextLine()) {
//			input.append(sc.nextLine()+System.lineSeparator());
//		}
//		
//		String contents = input.toString();
//		
//		sc.close();
//		
//		String old = oldLog;
//		System.out.println(old);
		String newIsh = newLog;
//		System.out.println(newIsh);
//		
//		contents.replace
//
//		contents = contents.replaceAll(old, newIsh);
		
//		try (FileWriter writer = new FileWriter("defect_logs.txt")) {
//			writer.append(contents);
//			writer.flush();
//		}
		
		int line = 0;
		
		File file = new File("defect_logs.txt");
		// File tempFile = new File("temp.txt");
		
//		  try{
//		        bufWriter =
//		            Files.newBufferedWriter(
//		                Paths.get("defect_logs.txt");}
//		               
		                
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("temp.txt"), Charset.forName("UTF8"));
//		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		// BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuffer input = new StringBuffer();
	    Scanner sc = new Scanner(file);
	    
	    while(sc.hasNext()) {
	    	if (line != index) {
	    		writer.write(sc.nextLine() + "\n");
	    		
	    		// System.out.println(sc.nextLine());
	    		line++;
	    	} else if (line == index) {
	    		line++;
	    		System.out.println("FOUND " + line);
	    		writer.write(newIsh + "\n");
	    		// sc.sk
	    		System.out.println(sc.nextLine());
	    	}
//	    		break;
//	    	} else if (line == (index+1)) {
//	    		line++;
//	    		continue;
//	    	}
	    }
	    
	    writer.close();
	    sc.close();
	    
	    // boolean successful = tempFile.renameTo(inputFile);
	    
	    
	    
		

		
	}
	

}


























