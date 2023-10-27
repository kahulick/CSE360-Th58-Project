package prototypes.Prototype1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import userInterfaceJavaFX.UserInterface;


public class Prototype1UI extends Application{

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PROTO 1!!!");
		
		Button back = new Button("Back");
//		back.setAlignment(Pos.TOP_RIGHT);
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
		
		Button btn = new Button();
        btn.setText("Display: 'Kaelyn Hulick says: Hello World!'");
//        btn.setAlignment(Pos.CENTER);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Kaelyn Hulick: Hello World!");
            }
        });
        
        VBox topButtons = new VBox(back);
        VBox midButtons = new VBox(btn);
        
        VBox buttons = new VBox(topButtons, midButtons);
        topButtons.setAlignment(Pos.TOP_LEFT);
        midButtons.setAlignment(Pos.CENTER);
		

        
		Scene scene = new Scene(buttons);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		
//        StackPane root = new StackPane();
//        root.getChildren().addAll(back, btn);
//        primaryStage.setScene(new Scene(root, 300.0, 250.0));
//        primaryStage.show();
    }

}
