package UserInterfaceJavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class userInterface extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		System.out.println("Tesing 123");
		System.out.println("Starting");
		
		primaryStage.setTitle("Testing UI");
		Button btn = new Button();
		btn.setText("Display: 'This is a test.'");
		btn.setOnAction(new EventHandler<>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Testing again....");	
			}
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
		
	}
	
}
