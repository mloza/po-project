package leczenie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ciphers.Cipher;

public class Badanie implements Serializable {
	private String nazwa;
	private String opis;
	private static String sep = "&";
	
	static List<Badanie> badania = new ArrayList<Badanie>();
	static Cipher cip = Cipher.ROT13;
	
	static {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		try {
			BufferedReader plik = new BufferedReader(new FileReader("data/badania.txt"));
			String linia;
			System.out.println("a---");
			while((linia = plik.readLine()) != null)
			{
				linia = cip.getDecipher(linia);
				Badanie ch = new Badanie();
				String[] values = linia.split(Pattern.quote(sep));
				System.out.println(values[0]);
				ch.setNazwa(values[0]);
				ch.opis = cip.getDecipher(values[1]);
				badania.add(ch);
			}
			System.out.println("a---");
		} catch (FileNotFoundException e) {
			System.out.println("Nie udało się wczytac badań");
			try {
				BufferedWriter plik = new BufferedWriter(new FileWriter("data/badania.txt"));
				String save = "";
				Badanie ch = new Badanie();
				ch.setNazwa("Badanie krwi");
				ch.opis = "Pobranie próbki krwi oraz badanie na niedobór składników";
				save += cip.getCipher(ch.getNazwa()+sep+ch.opis)+"\n";
				badania.add(ch);
				ch = new Badanie();
				ch.setNazwa("Pomiar ciśnienia");
				ch.opis = "Pomiar ciśnienia krwi";
				save += cip.getCipher(ch.getNazwa()+sep+ch.opis)+"\n";
				badania.add(ch);
				ch = new Badanie();
				ch.setNazwa("Badanie moczu");
				ch.opis = "Pobranie próbki moczu do badania aby sprawdzić czy wszystko ok";
				save += cip.getCipher(ch.getNazwa()+sep+ch.opis)+"\n";
				badania.add(ch);
				ch = new Badanie();
				ch.setNazwa("Elektroencefalogram");
				ch.opis = "Podłaczanie kabelków do głowy";
				save += cip.getCipher(ch.getNazwa()+sep+ch.opis)+"\n";
				badania.add(ch);
				ch = new Badanie();
				ch.setNazwa("Odporność na elektryczność");
				ch.opis = "Poddanie pacjęta elektrowstrząsom aby sprawdzić czy przeżyje, jeżeli mu się uda znaczy że jest z nim coś nie tak.";
				save += cip.getCipher(ch.getNazwa()+sep+ch.opis)+"\n";
				badania.add(ch);

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

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public static Badanie find(String string) throws BadanieNotFoundException {
		for(Badanie i: badania)
		{
			if(i.getNazwa().contentEquals(string)) return i;
		}
		throw new BadanieNotFoundException();
	}
}
