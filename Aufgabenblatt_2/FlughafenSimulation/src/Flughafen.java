import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flughafen extends Thread{
	private int anzahlFlugzeuge ;
	private List<Flugzeug> flugzeuge;
	private int id_counter = 0;
	private final int warteZeit =500; //in mili
	private int simTime;
	
	public Flughafen(int anzahl) {
		flugzeuge = new ArrayList<Flugzeug>();
		if(anzahl > 0) {
			for (int i = 0; i < anzahl; i++) {
				flugzeuge.add(erzeugeFlugzeug(this,(LocalTime.now().getSecond()*1000)));
				anzahlFlugzeuge = flugzeuge.size();
			}
		}else { throw new IllegalArgumentException();}
		simTime = 0;
	}

	public synchronized void landen(Flugzeug flug) {
		if(flug != null) {
			try{
				Thread.sleep(1500);
			}
			catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flug.istGelandet();
		}else {throw new IllegalArgumentException();}
	}
	
	@Override
	public void run() {
		System.out.println("Simolation Startet:");
		System.out.println(anzahlFlugzeuge + " Flugzeuge wollen auf unseren Spur landen!");
		
		
		System.out.println("Der Simulation dauert " + simTime/1000.0 +" sekunden vergangen" );
		
		
	}
	private Flugzeug erzeugeFlugzeug(Flughafen flughafen,int startTime) {
		id_counter++;
		return new Flugzeug("Flugzeug_"+id_counter, startTime, flughafen);
		
	}

}
