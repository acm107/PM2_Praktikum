package Aufgabe_1;


import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class GUI extends Application{
	
	@Override
	public void start(Stage arg0) throws Exception {
		init(arg0);
		
	}
	
	@SuppressWarnings("unchecked")
	public void init(Stage stage) {
		//Stage Eigenschaften
		stage.setTitle("Dateirecherche");
		stage.setWidth(480);
        stage.setHeight(600);
        Group root = new Group();
        Scene szene = new Scene(root);
        //add label1
        Label label1 = new Label("Gefundene Dateien");
        label1.setAlignment(Pos.TOP_CENTER);
        label1.setFont(new Font("Arial", 20));
        
        //add label2
        final Label label2 = new Label("Startordner");
        label1.setFont(new Font("Arial", 20));
        //add label3
        final Label label3 = new Label("Anzahl der Dateien");
        label1.setFont(new Font("Arial", 20));
        
        //add Tabelle
         TableView<Datei> table = new TableView<Datei>();
         table.setEditable(false);
        
        //
         ObservableList<Datei> datei = FXCollections.<Datei> observableArrayList();
         table.setItems(datei);
         
        // table Zeilen
        TableColumn<Datei , String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(120);
        nameCol.setCellValueFactory(new PropertyValueFactory<Datei, String>("name"));
        
        TableColumn<Datei , String> pfadCol = new TableColumn<>("Pfad");
        pfadCol.setMinWidth(210);
        pfadCol.setCellValueFactory(new PropertyValueFactory<Datei, String>("pfad"));
        
        TableColumn<Datei , Long> sizeCol = new TableColumn<>("Größe");
        sizeCol.setMinWidth(120);
        sizeCol.setCellValueFactory(new PropertyValueFactory<Datei, Long>("größe"));
        
        //Tablen Zeilen instalisieren
        table.getColumns().addAll(nameCol,pfadCol,sizeCol);
        
        //add Textfelde
        TextField txtF1 = new TextField();
        txtF1.setPromptText("startOrdner eingeben");
        
        TextField txtF2 = new TextField();
        txtF2.setPromptText("anzahl eingeben");
        
        //add start Button
        Button suchStrat = new Button("Dateien suchen");
		
        suchStrat.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	datei.clear();
		    	
		    	File ordner = new File(txtF1.getText());
		    	int anzahlDateien = 0;
		    	anzahlDateien = Integer.parseInt(txtF2.getText());
		    	Search dateisuche = new Search(ordner, anzahlDateien, datei);
		    	try {
					dateisuche.call();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
        
        
        HBox hb = new HBox();
        hb.getChildren().add(table);
        
        HBox hb2 = new HBox();
        hb2.setSpacing(80);
        
        hb2.getChildren().addAll(label2,txtF1);
        HBox hb3 = new HBox();
        hb3.setSpacing(30);
        hb3.getChildren().addAll(label3,txtF2);
        
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        
        vbox.getChildren().addAll(label1, hb,hb2,hb3,suchStrat);
        ((Group) szene.getRoot()).getChildren().addAll(vbox);
           //Szene darstellen
      		stage.setResizable(false);
      	    stage.setScene(szene);
      	    stage.show();
        
       
	}
	
	public static void main(String[] args) {
	    Application.launch(args);
	  }

}
