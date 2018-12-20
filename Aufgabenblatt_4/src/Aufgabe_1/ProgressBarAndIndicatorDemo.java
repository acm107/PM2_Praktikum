package Aufgabe_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * Demo-Anwendung für die Nutzung der 
 * Elemente ProgressBar und ProgressIndicator.
 * 
 *  HAW Hamburg
 * 
 * @author Jan Sudeikat
 * 
 */
public class ProgressBarAndIndicatorDemo extends Application {
 
    @Override
    public void start(Stage stage) {
 
    	// initial stellen die Elemente einen endlosen Verlauf dar
        ProgressBar progressBar = new ProgressBar();
        ProgressIndicator progressIndicator = new ProgressIndicator();
 
        TilePane root = new TilePane();
        
        root.setPadding(new Insets(10,10,10,10));        
        root.setHgap(20);
        root.setVgap(30);
        
        // unbestimmter Zustand
        Button button = new Button("Run");
        button.setOnAction(event -> {
        	progressIndicator.setProgress(-0.5);
        	progressBar.setProgress(-0.5);
        });
        
        // Endzustand setzen
        Button button2 = new Button("Stop");
        button2.setOnAction(event -> {
        	progressIndicator.setProgress(1.0);
        	progressBar.setProgress(1.0);
        });
        
        root.getChildren().addAll(
        		progressBar, 
        		progressIndicator,
        		button,
        		button2);
 
        Scene scene = new Scene(root, 250, 200);
 
        stage.setTitle("Demo ProgressBar & ProgressIndicator");
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}
