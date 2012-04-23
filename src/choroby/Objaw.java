package choroby;

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

public class Objaw implements Serializable {
	
	protected String nazwa = "Objaw";
	static List<Objaw> objawy = new ArrayList<Objaw>();
	static String sep = "$";
	static Cipher cip = Cipher.CAESAR;
	
	static {
		try {
			BufferedReader plik = new BufferedReader(new FileReader("data/objawy.txt"));
			String linia;
			System.out.println("---");
			while((linia = plik.readLine()) != null)
			{
				Objaw ch = new Objaw();
				String[] values = linia.split(Pattern.quote(sep));
				ch.setNazwa(cip.getDecipher(values[0]));
				objawy.add(ch);
			}
			System.out.println("---");
		} catch (FileNotFoundException e) {
			try {
				BufferedWriter plik = new BufferedWriter(new FileWriter("data/objawy.txt"));
				String save = "";
				Objaw ch = new Objaw();
				ch.setNazwa("Ból brzucha");
				save += cip.getCipher(ch.getNazwa())+"\n";
				objawy.add(ch);
				ch = new Objaw();
				ch.setNazwa("Biegunka");
				save += cip.getCipher(ch.getNazwa())+"\n";
				objawy.add(ch);
				ch = new Objaw();
				ch.setNazwa("Ból głowy");
				save += cip.getCipher(ch.getNazwa())+"\n";
				objawy.add(ch);
				ch = new Objaw();
				ch.setNazwa("Gorączka");
				save += cip.getCipher(ch.getNazwa())+"\n";
				objawy.add(ch);
				ch = new Objaw();
				ch.setNazwa("Krwawnienie z nosa");
				save += cip.getCipher(ch.getNazwa())+"\n";
				objawy.add(ch);

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
	
	public static Objaw find(String string) throws ObjawNotFoundException {
		for(Objaw i: objawy)
		{
			if(i.getNazwa().contentEquals(string)) return i;
		}
		throw new ObjawNotFoundException();
	}
	
	public Objaw() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Object obj) {
		if(obj instanceof String && ((String) obj).contentEquals(this.getNazwa())) return true;
		else if(obj instanceof Objaw && ((Objaw) obj).getNazwa().contentEquals(this.getNazwa())) return true;
		else return false;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public static List<Objaw> getAll() {
		return objawy;
	}
}
