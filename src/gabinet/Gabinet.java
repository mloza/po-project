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

public class Gabinet {
	private String typGabinetu = "Standardowy";
	private String typOswietlenia = "Standardowy";
	private Cipher ciph = Cipher.ADFGVC;
	private static String sep = "$";
	static ArrayList<Gabinet> gabinety = new ArrayList<Gabinet>();

	static {
		try {
			BufferedReader plik = new BufferedReader(new FileReader(
					"data/gabinety.txt"));
			String linia;
			while ((linia = plik.readLine()) != null) {
				Gabinet gab = new Gabinet();
				String[] values = linia.split(Pattern.quote(sep));
				gab.setTypGabinetu(values[0]);
				gab.setTypOswietlenia(values[1]);
				gabinety.add(gab);
			}
			System.out.println("---");
		} catch (FileNotFoundException e) {
			try {
				BufferedWriter plik = new BufferedWriter(new FileWriter(
						"data/gabinety.txt"));
				String save = "";
				Gabinet gab = new Gabinet();
				gab.setTypGabinetu("Standardowy");
				gab.setTypOswietlenia("Oświetlenie ogólne");
				save += gab.getTypGabinetu() + sep + gab.getTypOswietlenia()
						+ "\n";
				gab = new Gabinet();
				gab.setTypGabinetu("Zabiegowy");
				gab.setTypOswietlenia("Oświetlenie złożone");
				save += gab.getTypGabinetu() + sep + gab.getTypOswietlenia()
						+ "\n";
				gab = new Gabinet();
				gab.setTypGabinetu("Operacyjny");
				gab.setTypOswietlenia("Oświetlenie złożone oraz Lampy Operacyjne");
				save += gab.getTypGabinetu() + sep + gab.getTypOswietlenia()
						+ "\n";
				gab = new Gabinet();
				gab.setTypGabinetu("Okulistyczny");
				gab.setTypOswietlenia("Oświetlenie szczelinowe");
				save += gab.getTypGabinetu() + sep + gab.getTypOswietlenia()
						+ "\n";
				plik.append(save);
				plik.close();
			} catch (IOException e1) {
				System.out.println("Nie Bangla");
				System.exit(1010);
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void setTypGabinetu(String typGabinetu) {
		this.typGabinetu = typGabinetu;
	}

	public String getTypGabinetu() {
		return typGabinetu;
	}

	public String getTypOswietlenia() {
		return typOswietlenia;
	}


	public void setTypOswietlenia(String typOswietlenia) {
		this.typOswietlenia = typOswietlenia;
	}

	public Object getSprzetString() {
		return "Brak";
	}

}
