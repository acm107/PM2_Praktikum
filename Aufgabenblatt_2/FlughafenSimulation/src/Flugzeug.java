import java.time.LocalTime;
import java.util.Random;

/**
 * 
 * @author Ehsan , Martin
 *
 * 
 * 
 */

public class Flugzeug extends Thread {

	private Flughafen flughafen;
	private String id;
	private int flugdauer;
	private int startzeit;
	private Status status;
	public int simTime; // zeit
	public Flugzeug(String id, int startTime, Flughafen flughafen) {
		if (flughafen != null) {
			this.flughafen = flughafen;
		}
		else {
			throw new IllegalArgumentException();
		}
		flugdauer = randomZahl();
		this.id = id;
		this.startzeit = startTime;
		this.status = Status.IM_FLUG;
	}

	@Override
	public void run() {

		System.out.println(id + " befindet sich jetzt im Flug.  -->"+(startzeit)+" D: "+ flugdauer +" "+simTime);
		while (!isInterrupted()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (status) {
			case IM_FLUG:
				if (simTime - startzeit< flugdauer) {
					status = Status.IM_LANDING;
					//startzeit = simTime;
					//flugdauer = 1500;
					System.out.println(id + " befindet sich jetzt im Landing. -->"+ simTime);
				}
				break;
			case IM_LANDING:
				if (simTime - startzeit <flugdauer) {
					flughafen.landen(this);
					System.out.println(id + " ist gelandet.--> "+simTime);
					this.interrupt();
				}
				break;
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

		return getClass().getName() + this.getName();
	}

	public void setZeit(int zeit) {
		if (zeit >= 0) {
			this.simTime = zeit;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// -------------------------Private Methode---------------------------

	/*
	 * 
	 * return ein zufallwert in Milli.
	 * 
	 */

	private int randomZahl() {
		int min = 3000;
		int max = 9000;

		return new Random().nextInt(max - min) + min;
	}
}