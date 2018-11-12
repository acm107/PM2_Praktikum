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
	public int zeit;
	
	public Flugzeug(String id , int startTime, Flughafen flughafen) {
		if(flughafen != null) {
			this.flughafen = flughafen;
		}
		else {throw new IllegalArgumentException();}
		flugdauer = randomZahl();
		this.id = id;
		this.startzeit = startTime;
		this.status = Status.IM_FLUG;
		
	}
	
	@Override
	public void run() {
		System.out.println(id +" befindet sich jetzt im Flug. Zeit: "+ startzeit);
			
		
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
			this.zeit = zeit;
		}else {throw new IllegalArgumentException();}
	}
	public Status getStatus() {
		return status;
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
