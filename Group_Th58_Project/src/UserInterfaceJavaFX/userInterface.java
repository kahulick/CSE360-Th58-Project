package UserInterfaceJavaFX;

import prototypes.*; // be able to call our prototype classes
import prototypes.Prototype1.Prototype1UI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class userInterface extends Application {
	
//	private static 
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		System.out.println("Tesing 123");
		System.out.println("Starting");
		
		Button btn1 = new Button("Prototype 1: Generated Story Points Verification");
		Button btn2 = new Button("Prototype 2: Garbage clearance and Data Allocation");
		Button btn3 = new Button("Prototype 3: Database Usage and Information Storage");
		Button btn4 = new Button("Prototype 4: Specific Team Space Implementation");
		Button btn5 = new Button("Prototype 5: Proper Anonymization For Employees");
		
		VBox btns = new VBox(btn1, btn2, btn3, btn4, btn5);
		btns.setAlignment(Pos.CENTER);
		btns.setSpacing(10);
		
		Scene scene = new Scene(btns);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();

		btn1.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Testing again....");	
				Prototype1UI prototype1ui = new Prototype1UI();
				try {
					prototype1ui.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn2.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Testing again....");
				Prototype2 prototype2 = new Prototype2();
				try {
					prototype2.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn3.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Testing again....");
				Prototype3 prototype3 = new Prototype3();
				try {
					prototype3.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn4.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Testing again....");
				Prototype4 prototype4 = new Prototype4();
				try {
					prototype4.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btn5.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Testing again....");
				Prototype5 prototype5 = new Prototype5();
				try {
					prototype5.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
//		
//		
//		StackPane root = new StackPane();
////		root.getChildren().add(btn1);
//		root.getChildren().addAll(btn1, btn2);
//		primaryStage.setScene(new Scene(scene, 500, 500));
//		primaryStage.show();
//		
//	}
	
	}
}
