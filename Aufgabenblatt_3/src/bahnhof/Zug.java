package bahnhof;

public class Zug {
	private String zugID;
	public Zug(String zugID) {
		this.zugID = zugID;
	}
	@Override
	public String toString() {
		return "ZugNr "+ zugID;
	}

}
