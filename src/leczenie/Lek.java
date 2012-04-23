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

public class Lek implements Serializable {
	private String nazwa;
	
	static String sep = "$";
	
	static List<Lek> dostepneLeki = new ArrayList<Lek>();
	
	static {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		try {
			BufferedReader plik = new BufferedReader(new FileReader("data/leki.txt"));
			String linia;
			System.out.println("a---");
			while((linia = plik.readLine()) != null)
			{
				Lek ch = new Lek();
				String[] values = linia.split(Pattern.quote(sep));
				System.out.println(values[0]);
				ch.setNazwa(values[0]);
				dostepneLeki.add(ch);
			}
			System.out.println("a---");
		} catch (FileNotFoundException e) {
			System.out.println("Nie udało się wczytac badań");
			try {
				BufferedWriter plik = new BufferedWriter(new FileWriter("data/leki.txt"));
				String save = "";
				Lek ch = new Lek();
				ch.setNazwa("Rutinoscorbin");
				save += ch.getNazwa()+"\n";
				dostepneLeki.add(ch);
				ch = new Lek();
				ch.setNazwa("Apap");
				save += ch.getNazwa()+"\n";
				dostepneLeki.add(ch);
				ch = new Lek();
				ch.setNazwa("Lek 1");
				save += ch.getNazwa()+"\n";
				dostepneLeki.add(ch);
				ch = new Lek();
				ch.setNazwa("Panadol");
				save += ch.getNazwa()+"\n";
				dostepneLeki.add(ch);
				ch = new Lek();
				ch.setNazwa("Scroll of resurection");
				save += ch.getNazwa()+"\n";
				dostepneLeki.add(ch);

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
	
	public static Lek find(String lek) throws LekNotFoundException
	{
		for(Lek i: dostepneLeki)
		{
			System.out.println("-- "+i.getNazwa() + ":" + lek);
			if(i.getNazwa().contentEquals(lek)) return i;
		}
		throw new LekNotFoundException();
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Lek && ((Lek) o).getNazwa() == this.getNazwa()) return true;
		else return false;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

}
