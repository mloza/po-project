package people;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Administrator extends Worker {
	private String login;
	Occupation type;

	String ask;
	Scanner read = new Scanner(System.in);

	public Administrator(String login, String surname, String name,
			Occupation type) {
		super(surname, name, type);
		this.setLogin(login);
	}

	protected String getLogin() {
		return login;
	}

	private void setLogin(String login) {
		this.login = login;
	}

	/*
	 * w run sie odbywa caly ruch wewnatrz kazdego konta, opcji w administratorze 3:
	 * ActionC - tworzy konto
	 * ActionD - usuwa konto
	 * ActionH - sprawdza konto
	 */
	public void run() {
		System.out
				.println("Co teraz? (u)tworz nowe konto, (s)kasuj konto, s(p)rawdz stan konta");
		// try{
		ask = read.nextLine();
		if (ask.equals("u")) {
			ActionC create = new ActionC();
			create.run();
		}
		if (ask.equals("s")) {
			ActionD delete = new ActionD();
			delete.run();
		}

		if (ask.equals("p")) {
			ActionH check = new ActionH();
			check.run();
		}

	}

	/*
	 * Sprawdzanie konta, wyszukuje po loginie, tak jak w mainie, .getHeaderCount zwraca inta ile jest naglowkow
	 * .getHeader(int) wyswietla nazwe po numerze
	 * .get(int) wyswietla zawartosc komorki po numerze naglowka
	 */
	class ActionH {
		protected void run() {
			try {
				CsvReader search = new CsvReader("users.csv");
				search.readHeaders();

				System.out
						.println("Podaj login uzytkownika ktorego chcesz wyswietlic");
				ask = read.nextLine();

				while (search.readRecord()) {
					if (ask.equals(search.get("LOGIN"))) {
						for (int i = 0; i < search.getHeaderCount(); i++) {
							System.out.println(search.getHeader(i) + ": "
									+ search.get(i));
						}
					}
				}
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	/*
	 * Usuniecie konta, tu mi niestety biblioteka nie przyszla z pomoca, wiec po prostu tworze sobie tempa do ktorego zapisuje wszystkie
	 * linie poza ta w ktorej jest wzorzec, a wzorzec pobieram przez .split(String) ktore zwraca tabele, ogolnie tak bedzie mozna
	 * sie do kazdego miejsca dostac wiec jesli biblioteka czegos nie daje to to jest najprostszy sposob
	 * 
	 * jest cos takiego jak klasa RandomAccesFile ktora ponoc mozna zrobic to lepiej, ale nie znalazlem jakiegos latwego opisu, a to juz
	 * dzialalo wiec zostawilem (to tez bylo czesto podawane jako dobry sposob)
	 * 
	 * BufferedReader czyta po linijce, writer zapisuje przez .write, .flush czysci jakas pamiec, jak tego nie ma to robi jakies 
	 * straszne sadze
	 * 
	 * na koniec jak sie plikow nie pozamyka to tez sie dzieja sadze
	 */
	class ActionD {
		protected void run() {
			try {

				File in = new File("users.csv");
				File tmp = new File("tmp.csv");

				BufferedReader reader = new BufferedReader(new FileReader(in));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));

				String curr;
				String[] temp;
				System.out.println("Podaj login uzytkownika");
				ask = read.nextLine();
				while ((curr = reader.readLine()) != null) {
					temp = curr.split(",");
					if (!temp[0].equals(ask)) {
						writer.write(curr + "\n");
						writer.flush();
					}
				}
				reader.close();
				writer.close();
				in.delete();
				tmp.renameTo(in);

			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	/*
	 * Dodawanie nowego usera, przyjemnie zrobione przez biblioteke do csv, Writer dostaje w  konstruktorze plik i separator
	 * pozniej wszystko przez .write(String) a na koniec linijki trzeba zrobic .endRecord() na koniec pliku .close()
	 * 
	 * ps.wiem ze niektore opisy sa oczywiste, stwierdzilem ze wole pisac tak, niz pozniej zapominac np zamknac pliku ;p
	 */
	class ActionC {
		protected void run() {
			String of = "users.csv";
			try {
				CsvWriter saveNew = new CsvWriter(new FileWriter(of, true), ',');

				System.out.println("wprowadz login");
				ask = read.nextLine();
				saveNew.write(ask);

				System.out.println("wprowadz haslo");
				ask = read.nextLine();
				saveNew.write(ask);

				System.out.println("wprowadz typ");
				ask = read.nextLine();
				saveNew.write(ask);

				System.out.println("wprowadz nazwisko");
				ask = read.nextLine();
				saveNew.write(ask);

				System.out.println("wprowadz imie");
				ask = read.nextLine();
				saveNew.write(ask);

				saveNew.endRecord();

				saveNew.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

// ask = read.nextLine();