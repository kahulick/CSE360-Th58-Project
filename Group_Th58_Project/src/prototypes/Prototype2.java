package prototypes;

import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings({ "unused" })
public class Prototype2 extends Application {

	public void start(Stage primaryStage) throws Exception {
		
//		String name;	
//		String user_name;
//		String user_title;
//		String user_team;
//		int user_id;
//		
//		Scanner scan = new Scanner(System.in);
//		
//		System.out.println("PROTO 2!!!\nProper Anonymization For Employees");
//		
//		System.out.println("This is a prototype of how employee anonymization will work via a generalization approach.\n");
//		
//		System.out.print("Enter name: ");
//		name = scan.nextLine();
//		
//		System.out.print("Enter user name: ");
//		user_name = scan.nextLine();
//		
//		System.out.print("Enter numerical user ID: ");
//		user_id = scan.nextInt();
//		
//		System.out.print("Enter job title: ");
//		user_title = scan.nextLine();
//		user_title = scan.nextLine();
//		
//		System.out.print("Enter affiliated team for log: ");
//		user_team = scan.nextLine();
//		
//		
//		System.out.println("Stripping identifying information...\n");
//		System.out.print("Removing name...\nRemoving user name...\nRemoving user ID...\n\n");
//		
//		System.out.println("Data being transferred:\nJob Title: " + user_title + "\nAffiliated Teams: " + user_team +"\n");	
	}
	
	public void launch() {
		String name;	
		String user_name;
		String user_title;
		String user_team;
		int user_id;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("PROTO 2!!!\nProper Anonymization For Employees");
		
		System.out.println("This is a prototype of how employee anonymization will work via a generalization approach.\n");
		
		System.out.print("Enter name: ");
		name = scan.nextLine();
		
		System.out.print("Enter user name: ");
		user_name = scan.nextLine();
		
		System.out.print("Enter numerical user ID: ");
		user_id = scan.nextInt();
		
		System.out.print("Enter job title: ");
		user_title = scan.nextLine();
		user_title = scan.nextLine();
		
		System.out.print("Enter affiliated team for log: ");
		user_team = scan.nextLine();
		
		
		System.out.println("Stripping identifying information...\n");
		System.out.print("Removing name...\nRemoving user name...\nRemoving user ID...\n\n");
		
		System.out.println("Data being transferred:\nJob Title: " + user_title + "\nAffiliated Teams: " + user_team +"\n");
		
		scan.close();
		
	}
}