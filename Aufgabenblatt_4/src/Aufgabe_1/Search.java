package Aufgabe_1;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class Search extends Task<Boolean> {
	
	private File path;
	private int anzahl;
	private ObservableList<Datei> datei;
	
	public Search(File path, int anzahl, ObservableList<Datei> datei) {
		this.path = path;
		this.anzahl = anzahl;
		this.datei = datei;
	}

	@Override
	protected Boolean call() throws Exception {
		//pruefen ob der pfad ein Directory ist 
		if(path.isDirectory()) {
			//eine liste von der Dateien
			File[] files = path.listFiles();
			// 					
			int max = Math.min(files.length,anzahl);
			
			for(int i = 0;i < max ;i++) {
				//pruefen ob der inhlte der directory ein file ist
				if(files[i].isFile()) {
					datei.add(new Datei(files[i].getName(),files[i].getPath() ,files[i].length()));
				}
			}
		}
		return null;
	}
	
	
}
