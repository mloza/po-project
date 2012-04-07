package run;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import ludzie.Administrator;
import ludzie.Doctor;
import ludzie.Nurse;
import ludzie.Occupation;
import ludzie.Superciec;

public class AccManager {
	Scanner read = new Scanner(System.in);
	String ask;
	private File[] filesW; // tablica plikow w katalogu pracownikow
	private File dir = new File("workers"); // katalog pracownikow

	public void start() throws IOException {
		filesW = dir.listFiles();
		System.out.println("Zaloguj sie do systemu, podaj id");
		ask = read.nextLine();
		for (int i = 0; i < filesW.length; i++) {//przeszukuje cala liste szukajac w ktorym w pierwszej linijce jest odpowiedni login
			/*
			 * ogolnie to nie robi nic tworczego poza wyszukiwaniem po linijce w pliku i sprawdzaniu czy sie zgadza ze wzorem, nie mam 
			 * lepszej koncepcji jak to powinno wygladac tak szczerze
			 * kazdy plik mialby w swoich poczatkach te login haslo i typ, pozniej elementy w odpowiedniej kolejnosci do odpowiedniego konstruktora
			 * a pozniej dane,  ktorych w sumie nie mam pojecia jak przetwarzac
			 * 
			 * pewnie istnieje jakis lepszy sposob wyszukiwania tekstu w pliku niz skipowanie po linijce, ale w sumie nic lepszego nie
			 * wymyslilem, a ze to dziala to stwierdzilem ze zostawie, najwyzej ktos madrzejszy poprawi
			 * 
			 * Tworzac konkretny obiekt czlowieka przechodziloby sie do jego gui i bylyby akcje zwiazane z jego opcjami
			 * 
			 * z ludzi brakuje tylko pacjenta, ale ze to troche inna galaz to zostawilem to na razie w takiej wersji jak teraz
			 * 
			 * tylko administrator ma utworzona jakas logiczna metode w miare, ale jest to pozostalosc po mojej pierwszej koncepcji
			 * reszta klas ludzi jest po prostu przekopiowanym adminem chcialem zeby bylo wiecej, zeby sprawdzic czy dziala(a eclipse tak 
			 * ladnie zmienia nazwy gdzie trzeba))
			 */
			FileReader fr = new FileReader("workers/" + filesW[i].getName());
			Scanner f = new Scanner(fr);
			if (f.hasNext(ask)) {
				f.next();
				System.out.println("Podaj haslo");
				ask = read.nextLine();
				if (f.hasNext(ask)) {
					f.next();
					System.out.println("zalogowano");
					if (f.hasNext("ADMINISTRATOR")) {
						f.next();
						Administrator log = new Administrator(
								Occupation.ADMINISTRATOR, f.next(), f.next());
						System.out.println(log);
					}
					else if (f.hasNext("DOCTOR")) {
						f.next();
						Doctor log = new Doctor(
								Occupation.DOCTOR, f.next(), f.next());
						System.out.println(log);
					}
					else if (f.hasNext("NURSE")) {
						f.next();
						Nurse log = new Nurse(
								Occupation.NURSE, f.next(), f.next());
						System.out.println(log);
					}
					else if (f.hasNext("SUPERCIEC")) {
						f.next();
						Superciec log = new Superciec(
								Occupation.SUPERCIEC, f.next(), f.next());
						System.out.println(log);
					}
				} else {
					System.out.println("zle haslo");
				}
			}
		}

	}

}
