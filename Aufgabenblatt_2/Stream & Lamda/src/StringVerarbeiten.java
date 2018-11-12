import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class StringVerarbeiten {

	private String[] str;
	private Predicate<String> notNull;
	private Function<String, String> leerEntfernen;
	private Function<String, String> toUper;
	private Function<String, String> verkurzung;
	private Function<String, String> ersetzen;

	public StringVerarbeiten(String[] benutzereingabe) {
		if(benutzereingabe != null) {
			this.str = benutzereingabe;
		}
		else {throw new IllegalArgumentException("Falsche Eingabe");}
		notNull = (t) -> { return t != null; };
		leerEntfernen = (t) -> {return t.trim();};
		toUper = (t) -> {return t.toUpperCase();};
		verkurzung = (t) -> {
			if(t.length()>8) {
				t=t.substring(0, 8);
			}
			return t; };
			ersetzen = (t) -> { return t.replace("Ä","AE").replace("Ö","OE").replace("Ü","UE").replace("ß","SS");};
		
	}

	/* Die Methode erzeugt ein Stream-kette und dieser kette wird verarbeitet
	 * nach folgendes regel:
	 * 	-Entfernen von null-Eingaben  
	 *  -Abschneiden der Leerzeichen am Anfang und Ende
	 *  - Konvertierung von Klein- zu Großbuchstaben 
	 *  -Ersetzen Ä -> AE, Ö -> OE, Ü -> UE, ß -> SS 
	 *  - Kürzen der Strings auf maximal 8 Zeichen
	 */
	public List<String> String_Umwandelung(){
		Stream<String> str_Stream = Arrays.stream(str);
		return 	str_Stream.filter(notNull)
				.map(leerEntfernen)
				.map(toUper).map(ersetzen)
				.map(verkurzung)
				.collect(Collectors.toList());
		//System.out.println(Stream.of(str).filter(notNull).map(leerEntfernen).map(toUper).map(ersetzen).map(verkurzung).collect(Collectors.toList()));
		//return Stream.of(str).filter(notNull).map(leerEntfernen).map(toUper).map(ersetzen).map(verkurzung).collect(Collectors.toList());

	}
}
