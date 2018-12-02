package bahnhof;

import java.util.Observer;
import java.util.Observable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class BahnhofGUI extends Application implements Observer{
	private Stage stage; /** Eine Stage deklerieren */	
	private int anzahlderGleise;
	GridPane gridpane =new GridPane();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		anzahlderGleise = 5;
		init(stage);
	//	siim();
	}
	
	public void init(Stage stage){
		stage.setTitle("Rangierbahnhof");
		StackPane root = new StackPane();
		
		//Szene ordnung
		
		gridpane.setPadding(new Insets(10)); // distance of Top
		gridpane.setVgap(10);
		gridpane.setHgap(1);
		root.getChildren().add(gridpane);
		
		//add start Button
		Button start = startButton();
		gridpane.add(start,225,0);
		
		//zeichen der Gleise
		
		for (int i = 0; i < anzahlderGleise; i++) {
			gridpane.add(gleisBilder(), 0 , i);
		}
		
		//Szene darstellen
		stage.setScene(new Scene(root ,400,400));
		stage.setResizable(false);
		stage.show();
	}


	/**
	 * 
	 */
	private void bahnhofZeichen(Zug[] gleise){
		StackPane root = new StackPane();
		//Szene ordnung
		GridPane gridpane =new GridPane();
		gridpane.setPadding(new Insets(10)); // distance of Top
		gridpane.setVgap(10);
		gridpane.setHgap(1);
		root.getChildren().add(gridpane);
		
		for (int i = 0; i < gleise.length; i++) {
	    	 {if (gleise[i] == null) {
	    		 gridpane.add(gleisBilder(), 0, i);
	           } else
	            gridpane.add(gleisMitZugBilder(), 0, i);}
	      }
		
		//Szene darstellen
		Scene szene = new Scene(root, 400, 400);
	    stage.setScene(szene);
	    stage.show();

	}
	private Button startButton() {
		Simulator sim = new Simulator(anzahlderGleise);
		sim.getBahnhof().addObserver(this);
		Thread thread = new Thread(sim);
		
		Button start = new Button();
		start.setText("Start");
		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				thread.start();	
			}
		});
		return start;
	}
	
	
	/**
	 *  Zeichen des Gleises
	 */
	private Path gleisBilder() {
		Path path = new Path();
		
		MoveTo startPunkt = new MoveTo();
		startPunkt.setX(100);
		startPunkt.setY(0);
		
		LineTo line1 = new LineTo();
		line1.setX(0);
		line1.setY(0);
		
		LineTo line2 = new LineTo();
		line2.setX(0);
		line2.setY(50);
		
		LineTo line3 = new LineTo();
		line3.setX(100);
		line3.setY(50);
		
		path.getElements().add(startPunkt);
		path.getElements().add(line1);
		path.getElements().add(line2);
		path.getElements().add(line3);
	//    path.setTranslateY(50);
		path.setStroke(Color.BLACK);
		path.setStrokeWidth(3);
		return path;
	}
	
	/**
	 *  Zeichen des Gleises mit Zug
	 */
	private Path gleisMitZugBilder() {
		Path path = new Path();
		//Gleis zeichnen
		MoveTo startPunkt = new MoveTo();
		startPunkt.setX(100);
		startPunkt.setY(0);
		
		LineTo line1 = new LineTo();
		line1.setX(0);
		line1.setY(0);
		
		LineTo line2 = new LineTo();
		line2.setX(0);
		line2.setY(50);
		
		LineTo line3 = new LineTo();
		line3.setX(100);
		line3.setY(50);
		//zug zeichnen
		MoveTo startPunkt2 = new MoveTo();
		startPunkt2.setX(100);
		startPunkt2.setY(10);
		
		LineTo line21 = new LineTo();
		line21.setX(10);
		line21.setY(10);
		
		LineTo line22 = new LineTo();
		line22.setX(10);
		line22.setY(40);
		
		LineTo line23 = new LineTo();
		line23.setX(100);
		line23.setY(40);
		
		LineTo line24 = new LineTo();
		line24.setX(100);
		line24.setY(10);
		
		path.getElements().add(startPunkt);
		path.getElements().add(line1);
		path.getElements().add(line2);
		path.getElements().add(line3);
		path.getElements().add(startPunkt2);
		path.getElements().add(line21);
		path.getElements().add(line22);
		path.getElements().add(line23);
		path.getElements().add(line24);
		path.setStroke(Color.BLACK);
	  //  path.setTranslateY(30);
		path.setStrokeWidth(3);
		return path;
	}
	
		@Override
		public void update(Observable o, Object arg) {
			if(arg instanceof Zug[]) {
				Zug[] gleise = (Zug[]) arg;
			    Platform.runLater(() -> bahnhofZeichen(gleise));}
		}
		
		public static void main(String[] args) {
		    Application.launch(args);
		  }
}
