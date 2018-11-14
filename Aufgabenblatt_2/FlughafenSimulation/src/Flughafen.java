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
		this.anzahlFlugzeuge = anzahl;
		/*if(anzahl > 0) {
			for (int i = 0; i < anzahl; i++) {
				flugzeuge.add(erzeugeFlugzeug(this,(int)(LocalTime.now().getSecond()*1000))); //(LocalTime.now().getSecond()*1000)
				
			}
		}else { throw new IllegalArgumentException();}*/
		simTime = LocalTime.now().getNano()/1000;
		
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
	public  void run() {
		int index = 0;
		System.out.println("Simolation Startet:");
		System.out.println(anzahlFlugzeuge + " Flugzeuge wollen auf unseren Spur landen!");
		while(!isInterrupted() && index < anzahlFlugzeuge) {
			flugzeuge.add(erzeugeFlugzeug(this,(int)(LocalTime.now().getSecond()*1000)));
			flugzeuge.get(index).start();
			flugzeuge.get(index).setZeit(simTime);
			while(!flugzeuge.get(index).gelandet()) {
				try {
					Thread.sleep(warteZeit);
					simTime += warteZeit;
				} catch (InterruptedException e) {
					this.interrupt();
		    	}	
				
			}
			index++;
			
		}
		

		/*while(!flugzeuge.isEmpty()) {
			Iterator<Flugzeug> it = flugzeuge.iterator();
			it.next().start();
			while (	!it.next().gelandet()) {
			it.next().zeit =simTime;
			simTime += 500;
			}
			it.remove();
		}*/
		System.out.println("Der Simulation dauert " + simTime/1000.0 +" sekunden vergangen" );
		
		
	}
	private Flugzeug erzeugeFlugzeug(Flughafen flughafen,int startTime) {
		id_counter++;
		return new Flugzeug("Flugzeug_"+id_counter, startTime, flughafen);
		
	}

}
