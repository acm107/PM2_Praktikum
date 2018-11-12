
import static org.junit.Assert.*;

import  org.junit.Test;


public class StringVerarbeitenTest {

	private String[] erwartet = {"EINGABE","AEUSSERE","STRASSEN","EIN HAUS"};
	private String[] EINGABE = {"Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" };
	private StringVerarbeiten Klasse;
	public  StringVerarbeitenTest() {
	Klasse = new StringVerarbeiten(EINGABE);
	}
	@Test(expected = IllegalArgumentException.class)
	public void notNull() {
		new StringVerarbeiten(null);
	}
	
	
	@Test
	public void Test() {
		
		assertEquals(erwartet, Klasse.String_Umwandelung().toArray());
			
	}



}
