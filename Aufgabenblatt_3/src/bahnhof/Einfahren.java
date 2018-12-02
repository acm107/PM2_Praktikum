package bahnhof;

public class Einfahren implements Aufgabe{
	private int gleisNr;
	private Rangierbahnhof rangierbahnhof; 
	private Zug zug;
	public Einfahren(int gleisNr, Rangierbahnhof rangierbahnhof,Zug zug) {
		this.gleisNr = gleisNr;
		this.rangierbahnhof = rangierbahnhof;
		this.zug = zug;
	}
	
	@Override
	public void aufgabeMachen() {
		rangierbahnhof.zugEinfahren(gleisNr, zug);
		
	}

}
