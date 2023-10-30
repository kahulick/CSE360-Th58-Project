package prototypes;

import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import userInterfaceJavaFX.UserInterface;
@SuppressWarnings("unused")

public class Prototype2 extends Application{

	public void start(Stage primaryStage) throws Exception {
		
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
		
			
			

		}

	}

		
		
		

//
//
//
//
//
//
//		
//		Button back = new Button("Back");
//		Font font_back = new Font(25);
//		back.setPrefSize(100, 75);
//		back.setFont(font_back);
//		back.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                System.out.println("BACK");
//                UserInterface home = new UserInterface();
//				try {
//					home.start(primaryStage);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        });
//		
//        StackPane root = new StackPane();
//        root.getChildren().add(back);
//        root.setAlignment(Pos.TOP_LEFT);
//        primaryStage.setScene(new Scene(root, 300.0, 250.0));
//        primaryStage.setFullScreen(true);
//        primaryStage.show();
//		
	}

}