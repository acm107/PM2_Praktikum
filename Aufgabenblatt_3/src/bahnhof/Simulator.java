package bahnhof;

public class Simulator implements Runnable {
	private Rangierbahnhof rangierbahnhof;
	private Zug zug;
	private int dealy;
	private int zaehler = 0;
	
	public Simulator(int anzahlderGleise) {
		rangierbahnhof = new Rangierbahnhof(anzahlderGleise);
		this.dealy = 500; // ms
	}
	@Override
	public void run() {
		int gleisNummer = 0;
		Lockfuehrer lockfuehrer;
		
		while(true) {
			//setzt die Gleisnummmer wieder auf 0, wenn alle Gleise durch sind.
		      if (gleisNummer == rangierbahnhof.getZugAnzahl()) {
		        gleisNummer = 0;
		      }
		    //zufällig, ob der Lokführer ein- oder ausfährt.
		      int zufallszahl = (int) (Math.random() * 2);
		      if(zufallszahl == 0) {
		    	  zug = new Zug(""+zaehler); zaehler++;
		    	  lockfuehrer = new Lockfuehrer(new Einfahren(gleisNummer, rangierbahnhof, zug));  
		      }else {
		    	  lockfuehrer = new Lockfuehrer(new Ausfahren(gleisNummer, rangierbahnhof));
		      }
		      lockfuehrer.start();
		      gleisNummer++;
		      
		      try {
		          Thread.sleep(dealy);
		        } catch (InterruptedException e) {
		          // Exception - (wird nicht interrupted)
		        }
		}	
	}
	
	 public Rangierbahnhof getBahnhof() {
		    return rangierbahnhof;
		  }
	
}
