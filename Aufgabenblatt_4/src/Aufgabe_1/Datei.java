package Aufgabe_1;

public class Datei {
	private String name;
	private String pfad;
	private long size;
	
	public Datei(String name, String pfad, long size) {
		this.name = name;
		this.pfad = pfad;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public String getPfad() {
		return pfad;
	}

	public long getSize() {
		return size;
	}
	
}
