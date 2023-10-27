package prototypes;

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

public class Prototype5 extends Application{

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PROTO 5!!!");
		
		
		Button back = new Button("Back");
		Font font_back = new Font(25);
		back.setPrefSize(100, 75);
		back.setFont(font_back);
		back.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("BACK");
                UserInterface home = new UserInterface();
				try {
					home.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
        StackPane root = new StackPane();
        root.getChildren().add(back);
        root.setAlignment(Pos.TOP_LEFT);
        primaryStage.setScene(new Scene(root, 300.0, 250.0));
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
	}
}