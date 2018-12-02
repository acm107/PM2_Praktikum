package bahnhof;

public class Ausfahren implements Aufgabe {

	private int gleisNr;
	private Rangierbahnhof rangierbahnhof; 
	
	public Ausfahren(int gleisNr, Rangierbahnhof rangierbahnhof) {
		this.gleisNr = gleisNr;
		this.rangierbahnhof = rangierbahnhof;
	}
	
	@Override
	public void aufgabeMachen() {
		rangierbahnhof.zugAusfahren(gleisNr);
		
	}
}
