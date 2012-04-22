package choroby;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import ciphers.Cipher;

/**
 * Wszystko co potrzebne jest w mainie, jeżeli obiekt ma być odtworzony i przy odtwarzaniu ma być uruchomiony konstruktor to implementujemy interfejs Externalizable i dodajemy metody writeExternal i readExternal tak jak tutaj
 * Jeżeli konstruktor ma być pominięty to serializable i metody readObject i writeObject. Jeśli ma być bez szyfrowania itp można te motody pominąć przy serializable.
 * @author Scroot
 *
 */
public class Choroba implements Externalizable {
	private String nazwa = "asd";
	private String opis = "asd";
	// Możliwe wartości to CAESAR, ROT13, ADFGVC
	private Cipher ciph = Cipher.ADFGVC;
	private static String sep = "$";
	static List<Choroba> choroby = new ArrayList<Choroba>();
	static int ilosc = 0;
	
	private int index;
	
	static {
		try {
			BufferedReader plik = new BufferedReader(new FileReader("data/choroby.txt"));
			String linia;
			System.out.println("---");
			while((linia = plik.readLine()) != null)
			{
				Choroba ch = new Choroba();
				String[] values = linia.split(Pattern.quote(sep));
				ch.setNazwa(values[0]);
				ch.opis = values[1];
				choroby.add(ch);
			}
			System.out.println("---");
		} catch (FileNotFoundException e) {
			try {
				BufferedWriter plik = new BufferedWriter(new FileWriter("data/choroby.txt"));
				String save = "";
				Choroba ch = new Choroba();
				ch.setNazwa("Grypa");
				ch.opis = "Niezbyt przyjemna wiopsenna choroba";
				save += ch.getNazwa()+sep+ch.opis+"\n";
				choroby.add(ch);
				ch = new Choroba();
				ch.setNazwa("Przeziębienie");
				ch.opis = "Niezbyt przyjemna kolejna wiopsenna choroba";
				save += ch.getNazwa()+sep+ch.opis+"\n";
				choroby.add(ch);
				ch = new Choroba();
				ch.setNazwa("Biegunka");
				ch.opis = "Nieprzyjemna choroba, często towarzyszy jej ból brzucha, możliwe wymioty";
				save += ch.getNazwa()+sep+ch.opis+"\n";
				choroby.add(ch);
				ch = new Choroba();
				ch.setNazwa("Dżuma");
				ch.opis = "Śmiertelna choroba zabijająca ludzi dawno temu";
				save += ch.getNazwa()+sep+ch.opis+"\n";
				choroby.add(ch);
				ch = new Choroba();
				ch.setNazwa("Malaria");
				ch.opis = "Choroba tropikalna";
				save += ch.getNazwa()+sep+ch.opis+"\n";
				choroby.add(ch);

				plik.append(save);
				plik.close();
			} catch (IOException e1) {
				System.out.println("Fatalnie, nie da się z tym nic zrobić");
				System.exit(1010);
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public int hashCode() {
		return index;
	}
	
	/**
	 * Obiekty w liście też muszą być serializable lub externalizable
	 */
	protected List<Objaw> Objawy = new ArrayList<Objaw>();	
	/**
	 * Konstruktor dodaje objaw do listy, dla testów
	 */
	public Choroba()
	{
		this.index = ilosc++;
		//Objawy.add(new Objaw());
	}
		
	/**
	 * Metoda sterująca zapisem, co gdzie i kiedy ma być zapisane, tutaj powinno znaleźć się też szyfrowanie które zaraz dodam
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(Objawy);
			out.writeObject(getNazwa());
			out.writeObject(ciph.getCipher(opis));
			System.out.println("b1:"+opis);
			System.out.println("b:"+ciph.getCipher(opis));
	}

	/**
	 * Wczytywanie obiektu
	 */
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
			Objawy = (ArrayList<Objaw>)in.readObject();
			setNazwa((String)in.readObject());
			opis = ciph.getDecipher((String)in.readObject());
			System.out.println("c:"+opis);
	}

	public boolean equals(String o)
	{
		if(o.contentEquals(this.getNazwa())) return true;
		else return false;
	}
	
	public static Choroba find(String string) throws SicknessNotFoundException {
		for(Choroba i: choroby)
		{
			if(i.getNazwa().contentEquals(string)) return i;
		}
		throw new SicknessNotFoundException();
		
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
}
