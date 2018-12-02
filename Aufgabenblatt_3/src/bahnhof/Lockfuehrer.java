package bahnhof;

public class Lockfuehrer extends Thread{
	private Aufgabe aufgabe;
	
	public Lockfuehrer(Aufgabe aufgabe) {
		if(aufgabe == null) {
			throw new IllegalArgumentException();
		}
	this.aufgabe = aufgabe;
	}
	
	 /**
	   * fängt an seine Aufgabe zu erledigen.
	   */
	  @Override
	  public void run() {
	    aufgabe.aufgabeMachen();
	  }

}
