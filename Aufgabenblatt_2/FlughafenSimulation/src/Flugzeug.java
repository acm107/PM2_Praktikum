import java.time.LocalTime;
import java.util.Random;

/**
 * @author Ehsan , Martin
 *
 */ 
public class Flugzeug extends Thread{
	private Flughafen flughafen;
	private String id;				
	private int flugdauer;
	private int startzeit;
	private Status status ;
	public int simTime; //zeit
	
	public Flugzeug(String id , int startTime, Flughafen flughafen) {
		if(flughafen != null) {
			this.flughafen = flughafen;
		}
		else {throw new IllegalArgumentException();}
		flugdauer = randomZahl();
		this.id = id;
		this.startzeit = startTime;
		this.status = Status.IM_FLUG;
		this.simTime = 0;
		
	}
	
	@Override
	public  void run() {
		int LandingTime = 0; // in MilliSekunde	
		System.out.println(id +" befindet sich jetzt im Flug. START: "+ startzeit+", SIMTIME: "+simTime );
		while (!isInterrupted()) {
			switch (status) {
			case IM_FLUG:
				if(simTime - simTime < flugdauer) {
					status = Status.IM_LANDING;
					//startzeit = simTime;
					//flugdauer = 1500;
					System.out.println(id +" befindet sich jetzt im Landing. Zeit: "+ startzeit+", SIMTIME: "+simTime);
				}
				break;
			case IM_LANDING:
				if(simTime - simTime < flugdauer) {
					flughafen.landen(this);
					System.out.println(id +" ist gelandet. Zeit: "+ startzeit+", SIMTIME: "+simTime);
					this.interrupt();
				}

			
			}
			
		}

			
		
	}
	

	public void istGelandet() {
		status = Status.GELANDET;
	}
	
	public boolean gelandet() {
		return status == Status.GELANDET;
	}
	
	@Override
	public String toString() {
		return getClass().getName()+this.getName();
	}
	
	public void setZeit(int zeit) {
		if(zeit >= 0) {
			this.simTime = zeit;
		}else {throw new IllegalArgumentException();}
	}
//-------------------------Private Methode---------------------------
	/*
	 * return ein zufallwert in Milli.
	 */
	private int randomZahl() {
	     int min = 3000;
	     int max = 9000;
	     return  new Random().nextInt(max -min) + min;
	}
	

}
