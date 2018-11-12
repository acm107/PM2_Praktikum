
public class Simulator {
	
	public static void main(String[] args) {
		Flughafen flughafen = new Flughafen(3);
		flughafen.start();
		try {
			
			flughafen.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Simulation ist beendet");
	}
}
