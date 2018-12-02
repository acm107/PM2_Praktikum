package bahnhof;

import java.util.Observable;

public class Rangierbahnhof extends Observable{
	private int zugAnzahl;
	private Zug[] gleise;
	
	public Rangierbahnhof(int zugeAnzahl) {
		if(zugeAnzahl < 0) {return;}
		this.zugAnzahl = zugeAnzahl;
		this.gleise = new Zug[zugAnzahl];
	}
	public synchronized void zugEinfahren(int gleisNr, Zug zug) {
		if(gleisNr >= 0 && zug != null) {
		//wenn der gleis ist full, soll warten
			while(gleise[gleisNr] != null) {
				try {
					wait();
				} catch (InterruptedException e) {
					//Exeption
				}
			}
			gleise[gleisNr] = zug;
			System.out.println("Ein zug ist auf den Gleis " + gleisNr +" eingefahren. "+zug.toString());
			
			//BahnGUI aktualisieren
				setChanged();
				notifyObservers(gleise);
			//fahrgäste müssen aus dem Zug aussteigen bevor er wieder abfahren kann
		    try {
		      Thread.sleep(100);
		    } catch (InterruptedException e) {
		      // Exception - (wird nicht interrupted)
		    }
		    notify();
		}
	}
	public synchronized void zugAusfahren(int gleisNr) {
		if(gleisNr < 0) {return;}
		//wenn kein Zug da ist, kann kein Zug ausfahren
		while(gleise[gleisNr] == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// Exception
			}
		}
			String zugNr = gleise[gleisNr].toString();
			gleise[gleisNr] = null;
			System.out.println("Ein zug ist auf den Gleis " + gleisNr +" ausgefahren. "+zugNr);
			
			//BahnGUI aktualisieren
			setChanged();
			notifyObservers(gleise);
			
			try {
			      Thread.sleep(100);
			    } catch (InterruptedException e) {
			      // Exception - (wird nicht interrupted)
			    }
			   notify();
	}
	public int getZugAnzahl() {
		return zugAnzahl;
	}
	

}
