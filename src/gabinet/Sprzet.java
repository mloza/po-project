package gabinet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import ciphers.Cipher;

public class Sprzet{
	String nazwa = "nazwaSprzetu";
	int ilosc = 0;
	static ArrayList<Sprzet> jakiSprzet = new ArrayList<Sprzet>();
	static String sep = "$";
	static Cipher cip = Cipher.ADFGVC;
	
static {
	try {
		BufferedReader plik = new BufferedReader(new FileReader("data/sprzet.txt"));
		String linia;
		while((linia = plik.readLine()) != null)
		{
			Sprzet sztuka = new Sprzet();
			String[] values = linia.split(Pattern.quote(sep));
			sztuka.setNazwa(values[0]);
			sztuka.ilosc = (Integer.parseInt(values[1]));
			jakiSprzet.add(sztuka);
		}
	} catch (FileNotFoundException e) {
		try {
			BufferedWriter plik = new BufferedWriter(new FileWriter("data/sprzet.txt"));
			String save = "";
			Sprzet sztuka = new Sprzet();
			sztuka.setNazwa("Stół");
			sztuka.ilosc = 1;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Krzesło");
			sztuka.ilosc = 3;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Biurko");
			sztuka.ilosc = 2;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Aparat EKG");
			sztuka.ilosc = 1;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Aparat KTG");
			sztuka.ilosc = 0;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Stetoskop");
			sztuka.ilosc = 3;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Ciśnieniomierz");
			sztuka.ilosc = 3;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Strzykawka");
			sztuka.ilosc = 5;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);
			sztuka = new Sprzet();
			sztuka.setNazwa("Młoteczek Lekarski");
			sztuka.ilosc = 1;
			save += sztuka.getNazwa()+sep+sztuka.ilosc+"\n";
			jakiSprzet.add(sztuka);

			plik.append(save);
			plik.close();
		} catch (IOException e1) {
			System.out.println("Nie Bangla");
			System.exit(1010);
			e1.printStackTrace();
		}
		e.printStackTrace();
	} catch (Exception e)
	{
		e.printStackTrace();
	}
}

private void setNazwa(String nazwa) {
	this.nazwa = nazwa;
	
}

private String getNazwa() {
	return nazwa;
}
public static Sprzet find(String string) throws SprzetNotFoundException {
	for(Sprzet i: jakiSprzet)
	{
		if(i.getNazwa().contentEquals(string)) return i;
	}
	throw new SprzetNotFoundException();
}
public boolean equals(Object obj) {
	if(obj instanceof String && ((String) obj).contentEquals(this.getNazwa())) return true;
	else if(obj instanceof Sprzet && ((Sprzet) obj).getNazwa().contentEquals(this.getNazwa())) return true;
	else return false;
}
}
