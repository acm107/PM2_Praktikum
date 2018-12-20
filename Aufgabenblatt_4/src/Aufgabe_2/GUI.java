package Aufgabe_2;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		init(primaryStage);
	}

	public void init(Stage stage) {
		//Stage Eigenscheften
		stage.setTitle("Regex GUI");
		stage.setResizable(false);
		StackPane root = new StackPane();
		
		//TextFelder erstellen
		TextField regFil = new TextField();
		regFil.setPromptText("Regulere Ausdrucke");
		regFil.setPrefWidth(500);
		TextField inputFil = new TextField();
		inputFil.setPromptText("Eingabe");
		inputFil.setPrefWidth(500);
		
		//Label erstellen
		Label reglbl = new Label("Regex");
		Label inputlbl = new Label("Input");
		
		//Horizontal ordnung bei labels und TextFields
		HBox hb = new HBox();
		hb.getChildren().addAll(reglbl,regFil);
		hb.setSpacing(20);
		
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(inputlbl,inputFil);
		hb1.setSpacing(25);
		
		//Vertikal ordnung bei Hboxen
		VBox vb =  new VBox();
		vb.getChildren().addAll(hb,hb1);
		vb.setPadding(new Insets(15, 0, 0, 15));
		vb.setSpacing(10);
		
		root.getChildren().add(vb);
		
		//Regex pruefen
		inputFil.setOnAction(event -> {
			if(istPassend(regFil.getText(), inputFil.getText())){
				inputFil.setStyle("-fx-control-inner-background: green");
			}
			else {
				inputFil.setStyle("-fx-control-inner-background: red");
			}
	
		});
		//Scene Darstellen
		Scene szene = new Scene(root);
		stage.setScene(szene);
		stage.show();
	}
	
	private boolean istPassend(String regex ,String input) {
		boolean result = Pattern.matches(regex, input);
		return result;
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
