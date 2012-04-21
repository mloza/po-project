package choroby;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ciphers.Cipher;

import files.*;

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
	/**
	 * Obiekty w liście też muszą być serializable lub externalizable
	 */
	protected List<Objaw> Objawy = new ArrayList<Objaw>();
	private String[] fieldsToSave = {"nazwa", "opis"};
	
	/**
	 * Konstruktor dodaje objaw do listy, dla testów
	 */
	public Choroba()
	{
		Objawy.add(new Objaw());
	}
		
	/**
	 * Metoda ustawia wartości aby można było sprawdzić poprawnośc odczytu
	 */
	public void setValues()
	{
		this.nazwa = "ddd";
		this.opis = "fff";
	}
	
	/**
	 * Wypisuje wartości, też dla testów
	 */
	public void printValues()
	{
		System.out.println(this.nazwa);
	}
	
	/**
	 * Przykłąd zapisu i odczytu obiektów itp
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("start");
		
		//FileOp2.write(new Choroba(), "choroba.txt", "none");
		/**
		 * Zapis pojedynczego obiektu tworzonego przy writeObject
		 */
		try {
			ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("data/choroba.txt"));
			op.writeObject(new Choroba());
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Test odczytum tworze nowy obiekt domyślnie ma pola nazwa na asd ustawione
		 */
		Choroba testowa = new Choroba();
		System.out.println("Przed ustawienim");
		testowa.printValues();
		/**
		 * Ustawiam pole na ddd
		 */
		testowa.setValues();
		System.out.println("Po ustawienum");
		testowa.printValues();
		/**
		 * Wczytywanie
		 */
		try {
			ObjectInputStream op = new ObjectInputStream(new FileInputStream("data/choroba.txt"));
			testowa = (Choroba)op.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Po odczycie znów powinien mieć asd
		 */
		System.out.println("Po odczycie");
		testowa.printValues();
		
		/**
		 * Zapisywanie kolekcji samej
		 */
		List<Choroba> lista = new LinkedList<Choroba>();
		lista.add(new Choroba());
		Choroba ch = new Choroba();
		ch.setValues();
		lista.add(ch);
		//FileOp2.write(lista, "choroby.txt");
		
		/**
		 * Zapis
		 */
		try {
			ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("data/choroby.txt"));
			op.writeObject(lista);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * Odczyt
		 */
		List<Choroba> choroby = null;
		try {
			ObjectInputStream op = new ObjectInputStream(new FileInputStream("data/choroby.txt"));
			choroby = (LinkedList<Choroba>)op.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * Wypisanie wyników
		 */
		//FileOp2.read(choroby, "choroby.txt");
		for(Choroba f : choroby)
		{
			f.printValues();
		}
		
	}

	/**
	 * Metoda sterująca zapisem, co gdzie i kiedy ma być zapisane, tutaj powinno znaleźć się też szyfrowanie które zaraz dodam
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(Objawy);
			out.writeObject(nazwa);
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
			nazwa = (String)in.readObject();
			opis = ciph.getDecipher((String)in.readObject());
			System.out.println("c:"+opis);
	}
}
